#include <algorithm>
#include <ios>
#include <iostream>
#include <numeric>
#include <vector>

using namespace std;
void solve() {
    int n, k;
    cin >> n >> k;
    vector<int> prices(n);
    for (int i = 0; i < n; i++) {
        cin >> prices[i];
    }
    ranges::sort(prices, greater{});
    int sum = accumulate(prices.begin(), prices.end(), 0);
    int k_counter = 0;
    for (const int price : prices) {
        k_counter++;
        if (k_counter == k) {
            sum -= price;
            k_counter = 0;
        }
    }
    cout << sum;
}
int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);
    int t = 1;
    while (t--) {
        solve();
    }
}
