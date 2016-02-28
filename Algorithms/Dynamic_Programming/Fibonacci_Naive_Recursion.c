double F( int n ) {
    return ( n <= 1 ) ? 1.0 : F(n - 1) + F(n - 2);
}


// To avoid calculating values multiple times, store intermediate calculations in a table
// When storing intermediate results, this process is called memoization
// The root is memo
// We save (memoize) computed answers for possible later reuse, rather than re-computing the answer multiple times
