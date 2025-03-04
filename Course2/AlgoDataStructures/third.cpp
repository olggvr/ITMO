#include <iostream>
#include <stack>
#include <unordered_map>
#include <string>

using namespace std;

struct Scope {
    unordered_map<string, int> vars;
};

int main() {
    string line;
    stack<Scope> scopes;
    scopes.push(Scope());

    while (cin >> line) {
        if (line == "{") {
            scopes.push(scopes.top());
        } else if (line == "}") {
            scopes.pop();
        } else {
            size_t eq_pos = line.find('=');
            string var1 = line.substr(0, eq_pos);
            string value = line.substr(eq_pos + 1);

            if (isdigit(value[0]) || value[0] == '-') {
                scopes.top().vars[var1] = stoi(value);
            } else {
                int assigned_value = scopes.top().vars[value];
                scopes.top().vars[var1] = assigned_value;
                cout << assigned_value << endl;
            }
        }
    }
    return 0;
}
