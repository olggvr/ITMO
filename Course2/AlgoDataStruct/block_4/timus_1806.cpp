#include <iostream>
#include <vector>
#include <queue>
#include <unordered_map>
#include <string>
#include <algorithm>

using namespace std;

constexpr long long INFINITE = 1e18;

int totalDevices;
vector<int> prefixTime(10);
vector<string> deviceNumbers;
unordered_map<string, int> numberToIndex;
vector<long long> fastestPath;
vector<int> previousDevice;

void readInput() {
    cin >> totalDevices;
    for (int i = 0; i < 10; ++i) {
        cin >> prefixTime[i];
    }
    deviceNumbers.resize(totalDevices);
    for (int i = 0; i < totalDevices; ++i) {
        cin >> deviceNumbers[i];
        numberToIndex[deviceNumbers[i]] = i;
    }
}

int commonPrefixLength(const string& a, const string& b) {
    int match = 0;
    while (match < 10 && a[match] == b[match]) {
        ++match;
    }
    return match;
}

void tryUpdate(int from, const string& candidateNumber, priority_queue<pair<long long, int>, vector<pair<long long, int>>, greater<>>& pq) {
    auto it = numberToIndex.find(candidateNumber);
    if (it == numberToIndex.end()) return;
    int to = it->second;
    if (to == from) return;

    int prefix = commonPrefixLength(deviceNumbers[from], deviceNumbers[to]);
    long long cost = prefixTime[prefix];

    if (fastestPath[to] > fastestPath[from] + cost) {
        fastestPath[to] = fastestPath[from] + cost;
        previousDevice[to] = from;
        pq.emplace(fastestPath[to], to);
    }
}

void searchPaths() {
    fastestPath.assign(totalDevices, INFINITE);
    previousDevice.assign(totalDevices, -1);

    priority_queue<pair<long long, int>, vector<pair<long long, int>>, greater<>> pq;
    fastestPath[0] = 0;
    pq.emplace(0, 0);

    while (!pq.empty()) {
        auto [timeSpent, current] = pq.top();
        pq.pop();

        if (timeSpent > fastestPath[current]) continue;

        string original = deviceNumbers[current];

        for (int pos = 0; pos < 10; ++pos) {
            char saved = original[pos];
            for (char newChar = '0'; newChar <= '9'; ++newChar) {
                if (newChar == saved) continue;
                original[pos] = newChar;
                tryUpdate(current, original, pq);
            }
            original[pos] = saved;
        }

        for (int i = 0; i < 10; ++i) {
            for (int j = i + 1; j < 10; ++j) {
                if (original[i] == original[j]) continue;
                swap(original[i], original[j]);
                tryUpdate(current, original, pq);
                swap(original[i], original[j]);
            }
        }
    }
}

void outputResult() {
    if (fastestPath[totalDevices - 1] == INFINITE) {
        cout << "-1\n";
        return;
    }

    cout << fastestPath[totalDevices - 1] << '\n';

    vector<int> path;
    int position = totalDevices - 1;
    while (position != -1) {
        path.push_back(position + 1);
        position = previousDevice[position];
    }
    reverse(path.begin(), path.end());

    cout << path.size() << '\n';
    for (int fighter : path) {
        cout << fighter << ' ';
    }
    cout << '\n';
}

void solve() {
    readInput();
    searchPaths();
    outputResult();
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    solve();
}
