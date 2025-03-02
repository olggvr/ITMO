#include <iostream>
#include <map>
#include <stack>

using namespace std;
void solve() {
    string str;
    cin >> str;
    stack<char> current;
    stack<int> animals;
    stack<int> traps;
    map<int, int> result;
    int animals_count = 0;
    int traps_count = 0;
    for (size_t i = 0; i < str.length(); ++i) {
        if (!current.empty()) {
            char top = current.top();
            if (std::islower(str[i])) {
                animals_count++;
                if (std::toupper(str[i]) == top) {
                    current.pop();
                    result[traps.top()] = animals_count;
                    traps.pop();
                } else {
                    current.push(str[i]);
                    animals.push(animals_count);
                }
            } else {
                traps_count++;
                if (!animals.empty() && tolower(str[i]) == top) {
                    current.pop();
                    result[traps_count] = animals.top();
                    animals.pop();
                } else {
                    current.push(str[i]);
                    traps.push(traps_count);
                }
            }
        } else {
            if (std::islower(str[i])) animals.push(++animals_count);
            else traps.push(++traps_count);
            current.push(str[i]);
        }
    }
    if (!current.empty()) {
        cout << "Impossible";
        return;
    }
    cout << "Possible\n";
    for (auto item : result) {
        cout << item.second << " ";
    }
}
int main(){
    ios::sync_with_stdio(false); cin.tie(NULL);
    int t = 1;
    while (t--) {
        solve();
    }
}