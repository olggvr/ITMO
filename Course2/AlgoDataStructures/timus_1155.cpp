#include <iostream>
#include <map>
#include <vector>

bool is_empty(std::vector<int>* duons) {
    for (auto &item : duons) if (item != 0) return false;
    return true;
}

void solve() {
    std::map<int, char> char_mapper = {
        {0, 'A'},
        {1, 'B'},
        {2, 'C'},
        {3, 'D'},
        {4, 'E'},
        {5, 'F'},
        {6, 'G'},
        {7, 'H'}
    };
    std::vector<int> duons(8);
    std::vector<std::vector<int>> neighbors = {
        {1, 3, 4}, // A
        {0, 2, 5}, // B
        {1, 3, 6}, // C
        {0, 2, 7}, // D
        {0, 5, 7}, // E
        {1, 4, 6}, // F
        {2, 5, 7}, // G
        {3, 4, 6} // H
    };
    std::vector<std::string> actions;
    int sum = 0;
    for (int i = 0; i < 8; i++) {
        std::cin >> duons[i];
        sum += duons[i];
    }
    if (sum % 2 != 0) {
        std::cout << "IMPOSSIBLE\n";
        return;
    }

    while (!is_empty(&duons)) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                if (duons[i] != 0 && duons[j] != 0) {
                    duons[i]--;
                    duons[j]--;
                    std::string action = char_mapper[i] + char_mapper[j] + "-";
                    actions.push_back(action);
                    if (actions.size() > 1000) {
                        std::cout << "IMPOSSIBLE\n";
                        return;
                    }
                }
            }
        }
    }

}
int main() {
    std::ios::sync_with_stdio(false);std::cin.tie(nullptr);std::cout.tie(nullptr);
    int t = 1;
    while (t--) {
        solve();
    }
}