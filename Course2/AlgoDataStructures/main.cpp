#include <iostream>
#include <queue>
#include <stack>
#include <map>

using namespace std;
void solve() {
    map<string, stack<int>> vars;
    stack<vector<string>> scopes;
    scopes.emplace();

    string line;
    while (getline(cin, line)) {
        if (line == "{") {
            scopes.emplace();
        } else if (line == "}") {
            for (auto item : scopes.top()) vars[item].pop();
            scopes.pop();
        } else {
            string variable = line.substr(0, line.find('='));
            string value = line.substr(line.find('=') + 1);
            int number;
            if (isdigit(value[0]) || value[0] == '-') number = stoi(value);
            else {
                if (!vars.contains(value) || vars[value].empty()) number = 0;
                else number = vars[value].top();
                cout << number << endl;
            }
            scopes.top().push_back(variable);
            vars[variable].push(number);
        }
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int t = 1;
    while (t--) {
        solve();
    }
}