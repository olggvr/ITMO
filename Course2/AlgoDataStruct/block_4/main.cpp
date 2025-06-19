#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
const ll P1 = 1000000007LL;
const ll P2 = 1000000009LL;

struct hsh {
    size_t operator()(const pair<ll,ll>& p) const {
        return p.first ^ (p.second << 17);
    }
};

void solve() {
    int n;
    cin >> n;
    vector<int> a(n);
    for (auto &x : a) cin >> x;
    int maxA = *max_element(a.begin(), a.end());
    int maxPow = maxA + 21;
    vector<ll> pow2_1(maxPow), pow2_2(maxPow);
    pow2_1[0] = pow2_2[0] = 1;
    for (int i = 1; i < maxPow; ++i) {
        pow2_1[i] = (pow2_1[i-1] * 2) % P1;
        pow2_2[i] = (pow2_2[i-1] * 2) % P2;
    }
    vector<ll> prefix1(n+1), prefix2(n+1);
    for (int i = 0; i < n; ++i) {
        prefix1[i+1] = (prefix1[i] + pow2_1[a[i]]) % P1;
        prefix2[i+1] = (prefix2[i] + pow2_2[a[i]]) % P2;
    }
    unordered_map<pair<ll,ll>, int, hsh> cnt;
    cnt[{0,0}] = 1;
    ll ans = 0;
    for (int r = 1; r <= n; ++r) {
        for (int k = 0; k < maxPow; ++k) {
            ll want1 = (prefix1[r] - pow2_1[k] + P1) % P1;
            ll want2 = (prefix2[r] - pow2_2[k] + P2) % P2;
            auto key = make_pair(want1, want2);
            if (cnt.count(key)) ans += cnt[key];
        }
        cnt[{prefix1[r], prefix2[r]}]++;
    }
    cout << ans << '\n';
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    solve();
}
