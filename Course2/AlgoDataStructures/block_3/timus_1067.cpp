#include <iostream>
#include <map>
#include <string>
#include <sstream>

using namespace std;

void solve() {
    struct Folder {
        map<string, Folder*> inside;
    };

    Folder mem[50001];
    int next = 1;

    auto add = [&](Folder* root, const string& path) {
        string name;
        stringstream ss(path);
        while (getline(ss, name, '\\')) {
            if (!root->inside[name])
                root->inside[name] = &mem[next++];
            root = root->inside[name];
        }
    };

    auto show = [&](auto&& show, Folder* folder, int level) -> void {
        for (auto& [name, sub] : folder->inside) {
            cout << string(level, ' ') << name << '\n';
            show(show, sub, level + 1);
        }
    };

    int count;
    cin >> count;
    Folder* root = &mem[0];

    while (count--) {
        string input;
        cin >> input;
        add(root, input);
    }

    show(show, root, 0);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    solve();
}
