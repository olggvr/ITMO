#include <iostream>
#include <map>
#include <set>
#include <stack>
#include <vector>

using namespace std;

struct Scope{
    map<string, stack<int>> vars;
};

void solve() {
    stack<Scope> scopes;
    scopes.emplace();
    string line;

    while (cin >> line) {
        if (line.find('{') != string::npos) {
            scopes.emplace();
        } else if (line.find('}') != string::npos) {
            scopes.pop();
        } else {
            string variable = line.substr(0, line.find('='));
            string value = line.substr(line.find('=') + 1);
            int number;
            if (!value.empty() && (isdigit(value[0]) || value[0] == '-')) number = stoi(value);
            else {
                map<string, stack<int>> current = scopes.top().vars;
                if (current.find(value) != current.end() && !current[value].empty()) number = current[value].top();
                else number = 0;
                cout << number << '\n';
            }
            scopes.top().vars[variable].push(number);
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