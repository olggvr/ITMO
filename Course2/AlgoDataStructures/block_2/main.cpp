#include <ios>
#include <iostream>
#include <map>
#include <unordered_map>

using namespace std;
void solve() {
    string s;
    cin >> s;
    unordered_map<char, int> char_weight;
    map<char, int> char_frequency;
    for (char c = 'a'; c <= 'z'; c++) {
        char_weight[c] = cin.get();
    }
    for (char c : s) {
        if (char_frequency.contains(c)) char_frequency[c]++;
        else char_frequency[c] = 1;
    }
    string result;
    string tmp;
    for (auto item : char_frequency) {
        if (item.second > 1) {
            result += item.first;
        } else {
            tmp += item.first;
        }
    }
}
int main() {
    std::ios_base::sync_with_stdio(false); std::cin.tie(nullptr);
    int t = 1;
    while (t--) {
        solve();
    }
}
