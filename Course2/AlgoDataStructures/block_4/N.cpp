#include <iostream>
#include <vector>

using namespace std;

int n;
vector<int> destination;
vector<bool> explored;

void readData() {
    cin >> n;
    destination.resize(n + 1);
    explored.assign(n + 1, false);
    for (int i = 1; i <= n; ++i) {
        cin >> destination[i];
    }
}

bool traverse(int start) {
    vector<bool> inCurrentPath(n + 1, false);
    int current = start;
    while (!explored[current]) {
        explored[current] = true;
        inCurrentPath[current] = true;
        current = destination[current];
    }
    return inCurrentPath[current];
}

void solve() {
    readData();
    int smashed = 0;
    for (int pig = 1; pig <= n; ++pig) {
        if (!explored[pig]) {
            if (traverse(pig)) {
                smashed++;
            }
        }
    }
    cout << smashed << '\n';
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    solve();
}
