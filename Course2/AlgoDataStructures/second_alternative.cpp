#include <iostream>
#include <map>
#include <stack>

using namespace std;
void solve() {
    string str;
    cin >> str;
    stack<char> current;
    stack<int> animals;
    map<char, deque<int>> result;
    int animals_count = 0;

    for (size_t i = 0; i < str.length(); ++i) {
        if (!current.empty()) {
            char top = current.top();
            if (std::islower(str[i])) {
                animals_count++;
                if (std::toupper(str[i]) == top) {
                    current.pop();
                    result[top].push_front(animals_count);
                } else {
                    current.push(str[i]);
                    animals.push(animals_count);
                }
            } else {
                if (!animals.empty() && tolower(str[i]) == top) {
                    current.pop();
                    result[str[i]].push_back(animals.top());
                    animals.pop();
                } else {
                    current.push(str[i]);
                }
            }
        } else {
            if (std::islower(str[i])) animals.push(++animals_count);
            current.push(str[i]);
        }
    }

    if (!current.empty()) {
        cout << "Impossible";
        return;
    }
    cout << "Possible\n";
    for (char c : str) {
        if (isupper(c)) {
            cout << result[c].front() << " ";
            result[c].pop_front();
        }
    }
}

int main(){
    ios::sync_with_stdio(false); cin.tie(NULL);
    int t = 1;
    while (t--) {
        solve();
    }
}