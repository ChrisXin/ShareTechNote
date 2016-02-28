// Here is a bottom-up approach to calculating Fibonacci numbers:


double F( int n ) {
    if ( n <= 1 ) {
        return 1.0;
    }

    double a = 1.0, b = 1.0;

    for ( int i = 2; i <= n; ++i ) {
        a += b;
        b = a - b;
    }

    return a;
}
