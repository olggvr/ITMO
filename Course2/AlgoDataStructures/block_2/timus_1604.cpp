#include <iostream>
#include <vector>
#include <limits>
using namespace std;

pair<int, int> getMinMaxIndices(const vector<pair<int, int>>& signs) {
    int minIdx = -1, maxIdx = -1;
    int minAmt = numeric_limits<int>::max(), maxAmt = numeric_limits<int>::min();
    for (size_t i = 0; i < signs.size(); i++) {
        if (signs[i].first > 0) {
            if (signs[i].first >= maxAmt) {
                maxAmt = signs[i].first;
                maxIdx = i;
            }
            if (signs[i].first < minAmt) {
                minAmt = signs[i].first;
                minIdx = i;
            }
        }
    }
    return {minIdx, maxIdx};
}

void solve() {
    int k;
    cin >> k;
    vector<pair<int, int>> signs(k);
    int totalSigns = 0;
    for (int i = 0; i < k; i++) {
        cin >> signs[i].first;
        signs[i].second = i + 1;
        totalSigns += signs[i].first;
    }
    while (totalSigns > 0) {
        auto [minIdx, maxIdx] = getMinMaxIndices(signs);
        if (maxIdx != -1 && signs[maxIdx].first > 0) {
            cout << signs[maxIdx].second << ' ';
            signs[maxIdx].first--;
            totalSigns--;
        }
        if (minIdx != -1 && signs[minIdx].first > 0) {
            cout << signs[minIdx].second << ' ';
            signs[minIdx].first--;
            totalSigns--;
        }
    }
}
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    solve();
}
