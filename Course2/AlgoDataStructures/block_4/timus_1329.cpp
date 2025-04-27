#include <iostream>
#include <vector>
#include <unordered_map>
#include <stack>

using namespace std;

int numberOfEvents, numberOfQueries;
unordered_map<int, vector<int>> treeStructure;
unordered_map<int, int> timeIn, timeOut;
int globalTimer = 0;

void addEdge(int parent, int child) {
    treeStructure[parent].push_back(child);
}

void dfsTraversal(int startingNode) {
    stack<pair<int, int>> dfsStack;
    dfsStack.push({startingNode, 0});

    while (!dfsStack.empty()) {
        auto [currentNode, phase] = dfsStack.top();
        dfsStack.pop();

        if (phase == 0) {
            timeIn[currentNode] = globalTimer++;
            dfsStack.push({currentNode, 1});
            for (auto it = treeStructure[currentNode].rbegin(); it != treeStructure[currentNode].rend(); ++it) {
                dfsStack.push({*it, 0});
            }
        } else {
            timeOut[currentNode] = globalTimer++;
        }
    }
}

bool isAncestor(int ancestor, int descendant) {
    return timeIn[ancestor] <= timeIn[descendant] && timeOut[ancestor] >= timeOut[descendant];
}

int evaluateRelation(int a, int b) {
    if (isAncestor(a, b)) return 1;
    if (isAncestor(b, a)) return 2;
    return 0;
}

void readTree() {
    vector<int> rootNodes;
    cin >> numberOfEvents;
    for (int i = 0; i < numberOfEvents; ++i) {
        int nodeId, parentId;
        cin >> nodeId >> parentId;
        if (parentId == -1) {
            rootNodes.push_back(nodeId);
        } else {
            addEdge(parentId, nodeId);
        }
    }
    for (int root : rootNodes) {
        dfsTraversal(root);
    }
}

void processQueries() {
    cin >> numberOfQueries;
    while (numberOfQueries--) {
        int eventA, eventB;
        cin >> eventA >> eventB;
        cout << evaluateRelation(eventA, eventB) << '\n';
    }
}

void solve() {
    readTree();
    processQueries();
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    solve();
}
