#ifndef CA_UWATERLOO_ALUMNI_DWHARDER_DISJOINT_SETS
#define CA_UWATERLOO_ALUMNI_DWHARDER_DISJOINT_SETS

#include <cassert>
#include <cmath>
#include <ostream>

namespace Data_structures {
	class Disjoint_sets {
		public:
			Disjoint_sets( int );
			~Disjoint_sets();
			Disjoint_sets( Disjoint_sets const & );
			Disjoint_sets &operator=( Disjoint_sets const & );

			// Accessors
			int find( int ) const;
			bool is_singleton( int ) const;
			int height() const;
			int disjoint_sets() const;

			// Mutators
			void set_union( int, int );
			void clear();

		private:
			int array_size;
			int *parent;
			int *tree_height;
			int max_height;
			int num_disjoint_sets;

		// Friends
		friend std::ostream &operator<<(
			std::ostream &, const Disjoint_sets &
		);
	};

	/***************************************************
	 * *********************************************** *
	 * *                                             * *
	 * *    Constructors, Destructors, operator =    * *
	 * *                                             * *
	 * *********************************************** *
	 ***************************************************/

	Disjoint_sets::Disjoint_sets( int n ):
		array_size( std::max( 1, n ) ),
		parent( new int[array_size] ),
		tree_height( new int[array_size] ),
		max_height( 0 ),
		num_disjoint_sets( array_size )
	{
		for ( int i = 0; i < array_size; ++i ) {
			parent[i] = i;
			tree_height[i] = 0;
		}
	}

	Disjoint_sets::~Disjoint_sets() {
		delete [] parent;
		delete [] tree_height;
	}

	Disjoint_sets::Disjoint_sets( Disjoint_sets const &dset ):
		array_size( dset.array_size ),
		parent( new int[array_size] ),
		tree_height( new int[array_size] ),
		max_height( dset.max_height ),
		num_disjoint_sets( dset.num_disjoint_sets )
	{
		for ( int i = 0; i < array_size; ++i ) {
			parent[i] = dset.parent[i];
			tree_height[i] = dset.tree_height[i];
		}
	}

	Disjoint_sets &Disjoint_sets::operator=( Disjoint_sets const &dset ) {
		if ( this == &dset ) {
			return *this;
		}

		delete [] parent;
		delete [] tree_height;

		array_size = dset.array_size;
		parent = new int[array_size];
		tree_height = new int[array_size];
		max_height = dset.max_height;
		num_disjoint_sets = dset.num_disjoint_sets;

		for ( int i = 0; i < array_size; ++i ) {
			parent[i] = dset.parent[i];
			tree_height[i] = dset.tree_height[i];
		}

		return *this;
	}

	/***************************************************
	 * *********************************************** *
	 * *                                             * *
	 * *              Public Accessors               * *
	 * *                                             * *
	 * *********************************************** *
	 ***************************************************/

	/*********************************************************************
	 * int find( int n ) const
	 *
	 *  Find the representative of the set containing 'n'.
	 *    - The element at the root of the tree
	 *    - Update all elements in the path to the root
	 *      to point to the root
	 *********************************************************************/

	int Disjoint_sets::find( int n ) const {
		assert( n >= 0 && n < array_size );

		if ( parent[n] == n ) {
			return n;
		} else {
			parent[n] = find( parent[n] );
			return parent[n];
		}
	}

	/*********************************************************************
	 * bool is_singleton( int n ) const
	 *
	 *  A set is a singleton if:
	 *    - 'n' is the root of a tree, and
	 *    - The height of the tree rooted at 'n' is 0.
	 *********************************************************************/

	bool Disjoint_sets::is_singleton( int n ) const {
		assert( n >= 0 && n < array_size );

		return parent[n] == n && tree_height[n] == 0;
	}

	/*********************************************************************
	 * int height() const
	 *
	 *  Return the maximum height of any tree within the disjoint set.
	 *********************************************************************/

	int Disjoint_sets::height() const {
		return max_height;
	}

	/*********************************************************************
	 * int disjoint_sets() const
	 *
	 *  Return the number of disjoint sets.
	 *********************************************************************/

	int Disjoint_sets::disjoint_sets() const {
		return num_disjoint_sets;
	}

	/***************************************************
	 * *********************************************** *
	 * *                                             * *
	 * *               Public Mutators               * *
	 * *                                             * *
	 * *********************************************** *
	 ***************************************************/

	/*********************************************************************
	 * void set_union( int m, int n )
	 *
	 *  Take the union of the sets containing elements m and n.
	 *    - If m and n are already in the same set, do nothing.
	 *    - Otherwise:
	 *        - Decrease the number of disjoint sets,
	 *        - Attach the shorter tree onto the root of the larger tree,
	 *        - If the heights are equal, attach the set of the 2nd
	 *          argument to the set of the first argument, increment the
	 *          height of the tree being grafted onto, and possibly
	 *          update the maximum height of a tree.
	 *********************************************************************/

	void Disjoint_sets::set_union( int m, int n ) {
		assert( n >= 0 && n < array_size );
		assert( m >= 0 && m < array_size );

		// Map each to its representative
		m = find( m );
		n = find( n );

		if ( m == n ) {
			return;
		}

		--num_disjoint_sets;

		// Graft the shorter tree onto the root of the larger tree
		if ( tree_height[m] >= tree_height[n] ) {
			parent[n] = m;

			// If equal, graft 'n' onto 'm'
			if ( tree_height[m] == tree_height[n] ) {
				++( tree_height[m] );

				// Update the maximum height if necessary
				max_height = std::max(
					max_height, tree_height[m]
				);
			}
		} else {
			parent[m] = n;
		} 
	}

	/*********************************************************************
	 * void clear()
	 *
	 *  Convert the disjoint sets back to singletons.
	 *********************************************************************/

	void Disjoint_sets::clear() {
		// Don't do anything if it is already a set of singletons
		if ( num_disjoint_sets == array_size ) {
			return;
		}

		max_height = 0;
		num_disjoint_sets = array_size;

		for ( int i = 0; i < array_size; ++i ) {
			parent[i] = i;
			tree_height[i] = 0;
		}
	}

	/***************************************************
	 * *********************************************** *
	 * *                                             * *
	 * *                   Friends                   * *
	 * *                                             * *
	 * *********************************************** *
	 ***************************************************/

	/*********************************************************************
	 * out << dset;
	 *
	 *  Print the disjoint sets in the following format:
	 *
	 *   Size:           12
	 *   Height:         2
	 *   Number of Sets: 6
	 *                                     |         
	 *             |        |        |     |         
	 *   Parent:   0  3  2  8  6  5  6  8  8  9  3  0
	 *   Entry:    0  1  2  3  4  5  6  7  8  9 10 11
	 *   Sets: {0*,11}, {2}, {5}, {4,6*}, {1,3,7,8*,10}, {9}
	 *
	 *  The pipes represent the heights of the trees.
	 *  The last line prints the various sets and in any non-singleton
	 *  set the representative element is starred.
	 *********************************************************************/

	std::ostream &operator<<(
		std::ostream &out, const Disjoint_sets &dset
	) {
		out << "Size:           " << dset.array_size << std::endl;
		out << "Height:         " << dset.max_height << std::endl;
		out << "Number of Sets: " << dset.num_disjoint_sets << std::endl;

		int width = static_cast<int>(
			std::log10( dset.array_size - 1 )
		) + 2;

		for ( int h = dset.max_height; h > 0; h-- ) {
			out << "        ";

			for ( int i = 0; i < dset.array_size; ++i ) {
				out.width( width );

				if ( dset.tree_height[i] >= h ) {
					out << '|';
				} else {
					out << ' ';
				}
			}

			out << std::endl;
		}

		out << "Parent: ";

		for ( int i = 0; i < dset.array_size; ++i ) {
			out.width( width );
			out << dset.parent[i];
		}

		out << std::endl << "Entry:  ";

		for ( int i = 0; i < dset.array_size; ++i ) {
			out.width( width );
			out << i;
		}

		out << std::endl << "Sets: ";

		bool first_set = true;

		for ( int i = 0; i < dset.array_size; ++i ) {
			if ( dset.parent[i] == i ) {
				if ( first_set ) {
					out << "{";
					first_set = false;
				} else {
					out << ", {";
				}

				bool first_entry = true;

				for ( int j = 0; j < dset.array_size; ++j ) {
					if ( dset.find(j) == i ) {
						if ( first_entry ) {
							first_entry = false;
						} else {
							out << ",";
						}

						out << j;

						if ( i == j && dset.tree_height[i] != 0 ) {
							out << "*";
						}
					}
				}

				out << "}";
			}
		}

		return out;
	}
}

#endif