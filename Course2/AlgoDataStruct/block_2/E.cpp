#include <ios>
#include <iostream>
#include <vector>

bool is_configure_cows(const std::vector<int>& stables, const int k, const int mid) {
    int count = 1;
    int last_position = stables[0];
    for (size_t i = 1; i < stables.size(); i++) {
        if (stables[i] - last_position >= mid) {
            count++;
            last_position = stables[i];
        }
        if (count == k) return true;
    }
    return false;

}

void solve() {
    int n, k;
    std::cin >> n >> k;
    std::vector<int> stables(n);
    for (int i = 0; i < n; i++) {
        std::cin >> stables[i];
    }

    int lower_bound = 0, upper_bound = stables[n - 1] - stables[0];
    int max = 0;
    while (lower_bound <= upper_bound) {
        int mid = lower_bound + (upper_bound - lower_bound) / 2;
        if (is_configure_cows(stables, k, mid)) {
            max = mid;
            lower_bound = mid + 1;
        } else upper_bound = mid - 1;
    }
    std::cout << max;
}

int main() {
    std::ios_base::sync_with_stdio(false); std::cin.tie(nullptr);
    int t = 1;
    while (t--) {
        solve();
    }
}
