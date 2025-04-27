#include <iostream>
#include <stack>
#include <vector>

using namespace std;

int numberOfStudents, numberOfNotes;
vector<vector<int>> communication;
vector<int> teamAssignment;

void readInput() {
    cin >> numberOfStudents >> numberOfNotes;
    communication.assign(numberOfStudents + 1, {});
    teamAssignment.assign(numberOfStudents + 1, -1);

    for (int i = 0; i < numberOfNotes; ++i) {
        int from, to;
        cin >> from >> to;
        communication[from].push_back(to);
        communication[to].push_back(from);
    }
}

bool assignTeams(int start) {
    stack<int> pending;
    pending.push(start);
    teamAssignment[start] = 0;

    while (!pending.empty()) {
        int current = pending.top();
        pending.pop();

        for (int neighbor : communication[current]) {
            if (teamAssignment[neighbor] == -1) {
                teamAssignment[neighbor] = 1 - teamAssignment[current];
                pending.push(neighbor);
            } else if (teamAssignment[neighbor] == teamAssignment[current]) {
                return false;
            }
        }
    }
    return true;
}

bool validateGrouping() {
    for (int student = 1; student <= numberOfStudents; ++student) {
        if (teamAssignment[student] == -1) {
            if (!assignTeams(student)) {
                return false;
            }
        }
    }
    return true;
}

void solve() {
    readInput();
    if (validateGrouping()) {
        cout << "YES\n";
    } else {
        cout << "NO\n";
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    solve();
}
