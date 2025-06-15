#include <iostream>
#include <vector>
#include <unordered_map>
using namespace std;

typedef long long ll;

int get_max(const vector<int>& arr, int l, int r) {
    int pos = l;
    for (int i = l + 1; i <= r; ++i)
        if (arr[i] > arr[pos]) pos = i;
    return pos;
}

ll merge_sum(vector<int>& sub) {
    unordered_map<int, int> cnt;
    for (int v : sub) cnt[v]++;
    for (int i = 0; i < 70; ++i) {
        if (cnt.count(i) && cnt[i] >= 2) {
            int pairs = cnt[i] / 2;
            cnt[i + 1] += pairs;
            cnt[i] -= pairs * 2;
        }
    }
    ll res = 0;
    for (auto [val, num] : cnt) if (num) res += 1LL << val;
    return res;
}

vector<ll> all_merges(const vector<int>& arr, int l, int r) {
    vector<ll> res;
    vector<int> sub;
    for (int i = l; i <= r; ++i) {
        sub.push_back(arr[i]);
        res.push_back(merge_sum(sub));
    }
    return res;
}

ll solve(const vector<int>& arr, int l, int r) {
    if (l == r) return 1;

    int maxpos = get_max(arr, l, r);
    ll ans = 0;

    ll left = solve(arr, l, maxpos - 1);
    ll right = solve(arr, maxpos + 1, r);
    ans += left + right;

    vector<ll> left_merges, right_merges;
    if (maxpos - l < r - maxpos) {
        left_merges = all_merges(arr, maxpos, r);
        unordered_map<ll, int> right_map;
        vector<ll> right_part = all_merges(arr, l, maxpos - 1);
        for (ll v : right_part) right_map[v]++;
        for (ll x : left_merges)
            if (__builtin_popcountll(x) == 1) ans += right_map[x];
    } else {
        right_merges = all_merges(arr, l, maxpos);
        unordered_map<ll, int> left_map;
        vector<ll> left_part = all_merges(arr, maxpos + 1, r);
        for (ll v : left_part) left_map[v]++;
        for (ll x : right_merges)
            if (__builtin_popcountll(x) == 1) ans += left_map[x];
    }
    return ans;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int n;
    cin >> n;
    vector<int> arr(n);
    for (int& x : arr) cin >> x;
    cout << solve(arr, 0, n - 1) << '\n';
}
