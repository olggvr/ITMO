#include <iostream>
#include <stack>
#include <vector>

using namespace std;
void solve() {
    int totalBalls;
    cin >> totalBalls;

    vector<int> sequence(totalBalls);
    for (int &ball : sequence) {
        cin >> ball;
    }

    stack<int> pocket;
    int nextBall = 1, index = 0;

    for (; nextBall <= totalBalls; ++nextBall) {
        pocket.emplace(nextBall);

        while (!pocket.empty() && pocket.top() == sequence[index]) {
            pocket.pop();
            ++index;
        }
    }

    cout << (index == totalBalls ? "Not a proof" : "Cheater") << '\n';
}

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);
    solve();
}