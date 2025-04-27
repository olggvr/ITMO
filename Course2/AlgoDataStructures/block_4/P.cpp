#include <iostream>
#include <vector>

using namespace std;

int n;
vector<vector<int>> fuel;
vector<bool> visited;

void dfs(int u, int limit, const vector<vector<int>>& graph) {
    visited[u] = true;
    for (int v = 0; v < n; v++) {
        if (!visited[v] && graph[u][v] <= limit) {
            dfs(v, limit, graph);
        }
    }
}

bool isConnected(int limit) {
    visited.assign(n, false);
    dfs(0, limit, fuel);
    for (bool v : visited) {
        if (!v)
            return false;
    }

    vector<vector<int>> reversed(n, vector<int>(n));
    for (int i = 0; i < n; ++i)
        for (int j = 0; j < n; ++j)
            reversed[i][j] = fuel[j][i];

    visited.assign(n, false);
    dfs(0, limit, reversed);
    for (bool v : visited) {
        if (!v)
            return false;
    }

    return true;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> n;
    fuel.assign(n, vector<int>(n));
    int maxFuel = 0;

    for (int i = 0; i < n; ++i)
        for (int j = 0; j < n; ++j) {
            cin >> fuel[i][j];
            maxFuel = max(maxFuel, fuel[i][j]);
        }

    int left = 0, right = maxFuel, answer = maxFuel;

    while (left <= right) {
        int mid = (left + right) / 2;
        if (isConnected(mid)) {
            answer = mid;
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }

    cout << answer << '\n';

    return 0;
}
