#include <iostream>
#include <ctime>

#include "Disjoint_sets.h"
using namespace Data_structures;

int main() {
	int const N = 16;

	Disjoint_sets dset( N );

	std::cout << dset << std::endl << std::endl;

	for ( int i = 0; i < N/2; ++i ) {
		dset.set_union( rand() % N, rand() % N );
	}

	Disjoint_sets dset2 = dset;

	std::cout << dset << std::endl << std::endl;

	for ( int i = 0; i < N*N; ++i ) {
		dset.set_union( rand() % N, rand() % N );
	}

	std::cout << dset << std::endl << std::endl;

	std::cout << dset2 << std::endl << std::endl;

	dset2.clear();

	std::cout << dset2 << std::endl << std::endl;

	for ( int i = 0; 2*i < N; ++i ) {
		dset2.set_union( 2*i, 2*i + 1 );
	}

	for ( int i = 0; 4*i < N; ++i ) {
		dset2.set_union( 4*i, 4*i + 2 );
	}

	for ( int i = 0; 8*i < N; ++i ) {
		dset2.set_union( 8*i, 8*i + 4 );
	}

	for ( int i = 0; 16*i < N; ++i ) {
		dset2.set_union( 16*i, 16*i + 8 );
	}

	std::cout << dset2 << std::endl;

	srand( time( 0 ) );

	Disjoint_sets *ptr = new Disjoint_sets( 12 );

	for ( int i = 0; i < 6; ++i ) {
		ptr->set_union( rand() % 12, rand() % 12 );
	}

	std::cout << *ptr << std::endl;
	
	delete ptr;

	return 0;
}