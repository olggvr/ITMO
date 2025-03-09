#include <cstdint>
#include <iostream>
int64_t solve() {
    uint16_t b, c;
    int64_t a, d, k;
    std::cin >> a >> b >> c >> d >> k;
    int64_t prev = -1;
    while (k--) {
        a = a * b - c;
        if (a <= 0) return 0;
        if (a > d) return d;
        if (prev == a) return a;
        prev = a;
    }
    return a;
}
int main() {
    std::ios::sync_with_stdio(false);std::cin.tie(nullptr);std::cout.tie(nullptr);
    std::cout << solve();
}