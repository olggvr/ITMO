#include <iostream>
#include <vector>

using namespace std;
void solve() {
    int n;
    cin >> n;
    vector<int> a(n);
    int begin = 0, end = n == 1 ? 0 : 1;
    int begin_max = 0, end_max = end;

    for (int i = 0; i < n; ++i) {
        cin >> a[i];
        if (i > 1) {
            if (a[i] == a[i - 1] && a[i] == a[i - 2]) {
                if (end - begin > end_max - begin_max) {
                    begin_max = begin;
                    end_max = end;
                }
                begin = i - 1;
            } else {
                end = i;
            }
        }
    }

    if (end - begin > end_max - begin_max) cout << ++begin << " " << ++end;
    else cout << ++begin_max << " " << ++end_max;
}

int main(){
    ios::sync_with_stdio(false); cin.tie(NULL);
    int t = 1;
    while (t--) {
        solve();
    }
}