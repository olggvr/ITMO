#include <iostream>
#include <vector>
using namespace std;

bool isCombinable(const long long x) {
    return x > 0 && (x & (x - 1)) == 0;
}

void solve() {
    int n;
    const int MAX_VALUE = 1000000;
    cin >> n;
    vector<int> arr(n);
    vector<long long> cache(MAX_VALUE);
    for (int i = 0; i < MAX_VALUE; ++i) {
        cache[i] = 1LL << i;
    }
    for (int i = 0; i < n; ++i) {
        cin >> arr[i];
    }
    vector<long long> prefix(n + 1, 0);
    for (int i = 0; i < n; ++i) {
        prefix[i + 1] = prefix[i] + cache[arr[i]];
    }
    int result = 0;
    for (int l = 0; l < n; ++l) {
        for (int r = l + 1; r <= n; ++r) {
            if (isCombinable(prefix[r] - prefix[l])) result++;
        }
    }
    cout << result << endl;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    solve();
}
