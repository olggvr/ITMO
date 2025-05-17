#include <deque>
#include <iostream>
#include <string>

using namespace std;

void solve() {
    int q;
    cin >> q;
    deque<int> front_half, back_half;

    while (q--) {
        string action;
        cin >> action;

        if (action[0] == '+') {
            int val;
            cin >> val;
            back_half.emplace_back(val);
        } else if (action[0] == '*') {
            int val;
            cin >> val;
            front_half.emplace_back(val);
        } else {
            if (!front_half.empty()) {
                cout << front_half.front() << '\n';
                front_half.pop_front();
            } else {
                cout << back_half.front() << '\n';
                back_half.pop_front();
            }
        }

        if (front_half.size() < back_half.size()) {
            front_half.push_back(back_half.front());
            back_half.pop_front();
        } else if (front_half.size() > back_half.size() + 1) {
            back_half.push_front(front_half.back());
            front_half.pop_back();
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    solve();
}
