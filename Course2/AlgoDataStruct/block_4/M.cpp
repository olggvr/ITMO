#include <algorithm>
#include <iostream>
#include <queue>
#include <string>
#include <vector>

using namespace std;

void solve() {
    int n, m;
    cin >> n >> m;
    int start_x, start_y, end_x, end_y;
    cin >> start_x >> start_y >> end_x >> end_y;
    --start_x, --start_y, --end_x, --end_y;

    vector<string> grid(n);
    for (auto& row : grid)
        cin >> row;

    const int INF = 1e9;
    vector<vector<int>> cost(n, vector<int>(m, INF));
    for (int i = 0; i < n; ++i)
        for (int j = 0; j < m; ++j)
            cost[i][j] = (grid[i][j] == '.') ? 1 : (grid[i][j] == 'W' ? 2 : INF);

    vector<vector<int>> dist(n, vector<int>(m, INF));
    vector<vector<pair<int, int>>> prev(n, vector<pair<int, int>>(m, {-1, -1}));

    priority_queue<pair<int, pair<int, int>>, vector<pair<int, pair<int, int>>>, greater<>> pq;
    dist[start_x][start_y] = 0;
    pq.push({
        0, {start_x, start_y}
    });

    int dx[4] = {-1, 0, 1, 0};
    int dy[4] = {0, 1, 0, -1};
    char move_dir[4] = {'N', 'E', 'S', 'W'};

    while (!pq.empty()) {
        auto [d, pos] = pq.top();
        auto [x, y] = pos;
        pq.pop();
        if (d > dist[x][y])
            continue;

        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i], ny = y + dy[i];
            if (nx >= 0 && nx < n && ny >= 0 && ny < m && cost[nx][ny] != INF) {
                int nd = d + cost[nx][ny];
                if (nd < dist[nx][ny]) {
                    dist[nx][ny] = nd;
                    prev[nx][ny] = {x, y};
                    pq.push({
                        nd, {nx, ny}
                    });
                }
            }
        }
    }

    if (dist[end_x][end_y] == INF) {
        cout << -1 << '\n';
        return;
    }

    cout << dist[end_x][end_y] << '\n';
    string path;
    int x = end_x, y = end_y;
    while (x != start_x || y != start_y) {
        auto [px, py] = prev[x][y];
        for (int i = 0; i < 4; ++i)
            if (px + dx[i] == x && py + dy[i] == y)
                path += move_dir[i];
        x = px, y = py;
    }
    reverse(path.begin(), path.end());
    cout << path << '\n';
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    solve();
    return 0;
}
