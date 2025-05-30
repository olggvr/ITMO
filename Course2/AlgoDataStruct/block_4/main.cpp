#include <iostream>
#include <vector>
#include <unordered_map>
#include <cmath>

using namespace std;

bool isPowerOfTwo(int x) {
    return (x & (x - 1)) == 0;
}

void solve() {
    int n;
    cin >> n;
    vector<int> a(n);
    for (int i = 0; i < n; ++i) {
        cin >> a[i];
    }

    int res = 0;

    for (int l = 0; l < n; ++l) {
        unordered_map<int, int> freq;
        bool valid = true;
        for (int r = l; r < n; ++r) {
            freq[a[r]]++;
            if (!isPowerOfTwo(freq[a[r]])) {
                valid = false;
            }
            if (valid) {
                res++;
            } else {
                break;
            }
        }
    }

    cout << res << endl;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    solve();
}