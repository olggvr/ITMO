#include <algorithm>
#include <ios>
#include <iostream>
#include <unordered_map>
#include <vector>

using namespace std;

bool compare(const std::pair<char, int>& a, const std::pair<char, int>& b) {
    return a.second > b.second;
}

void solve() {
    string s;
    cin >> s;
    unordered_map<char, int> char_count;
    vector<pair<char, int>> char_weight;

    for (char c = 'a'; c <= 'z'; c++) {
        int w;
        cin >> w;
        char_weight.emplace_back(c, w);
    }
    for (char c : s) {
        char_count[c]++;
    }
    ranges::sort(char_weight.begin(), char_weight.end(), compare);
    string edge, middle;
    for (auto item : char_weight) {
        char character = item.first;
        if (char_count[character] > 1) {
            edge.push_back(character);
            char_count[character] -= 2;
        }
        string chars_to_add(char_count[character], character);
        middle.append(chars_to_add);
    }
    string result;
    result.reserve(s.size());
    result.append(edge);
    result.append(middle);
    result.append(edge.rbegin(), edge.rend());

    cout << result;
}

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    int t = 1;
    while (t--) {
        solve();
    }
}
