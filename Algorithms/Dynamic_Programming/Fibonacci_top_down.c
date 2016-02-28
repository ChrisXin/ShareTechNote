#include <map>

/* calculate the nth Fibonacci number */
double F( int n ) {
        static std::map<int, double> memo;
                // shared by all calls to F
                // the key is int, the value is double

        if ( n <= 1 ) {
                return 1.0;
        } else {
                if ( memo[n] == 0.0 ) {
                        memo[n] = F(n - 1) + F(n - 2);
                }

                return memo[n];
        }
}
