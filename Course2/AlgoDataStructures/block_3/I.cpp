#include <iostream>
#include <set>
#include <unordered_map>
#include <unordered_set>
#include <vector>

using namespace std;
void solve() {
  int totalCars, maxOnFloor, totalRequests;
  cin >> totalCars >> maxOnFloor >> totalRequests;

  vector<int> requests(totalRequests);
  for (int i = 0; i < totalRequests; ++i) {
    cin >> requests[i];
  }

  const int notUsed = totalRequests + 1;
  vector<int> nextUsage(totalRequests);
  vector<int> futurePosition(totalCars + 1, notUsed);

  for (int i = totalRequests - 1; i >= 0; --i) {
    nextUsage[i] = futurePosition[requests[i]];
    futurePosition[requests[i]] = i;
  }

  unordered_set<int> floorSet;
  floorSet.reserve(maxOnFloor * 2);
  unordered_map<int, int> nextIndex;
  nextIndex.reserve(maxOnFloor * 2);
  set<pair<int, int>> usageOrder;

  int actionCount = 0;

  for (int i = 0; i < totalRequests; ++i) {
    int carId = requests[i];
    int upcoming = nextUsage[i];

    if (floorSet.count(carId)) {
      usageOrder.erase({nextIndex[carId], carId});
      nextIndex[carId] = upcoming;
      usageOrder.insert({upcoming, carId});
    } else {
      ++actionCount;
      if (floorSet.size() == static_cast<size_t>(maxOnFloor)) {
        auto it = prev(usageOrder.end());
        int toRemove = it->second;
        usageOrder.erase(it);
        floorSet.erase(toRemove);
        nextIndex.erase(toRemove);
      }
      floorSet.insert(carId);
      nextIndex[carId] = upcoming;
      usageOrder.insert({upcoming, carId});
    }
  }

  cout << actionCount;
}

int main() {
  ios::sync_with_stdio(false);
  cin.tie(nullptr);
  solve();
  return 0;
}
