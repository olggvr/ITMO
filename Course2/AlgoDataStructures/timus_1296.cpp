#include <iostream>
#include <vector>

void solve() {
    int n;
    std::cin >> n;
    int sum = 0;
    int result = 0;
    int current;
    for (int i = 0; i < n; i++) {
        std::cin >> current;
        sum = std::max(current, sum + current);
        result = std::max(result, sum);
    }
    std::cout << result << '\n';
}
int main() {
    std::ios::sync_with_stdio(false);std::cin.tie(nullptr);std::cout.tie(nullptr);
    int t = 1;
    while (t--) {
        solve();
    }
}