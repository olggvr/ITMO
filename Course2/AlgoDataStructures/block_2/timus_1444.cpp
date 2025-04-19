#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>

using namespace std;
#define PI 3.142
const double NEGATIVE_INFINITY = -1e10;

struct Coord {
    long x, y;
    double angle;
    int index;
};

int totalPoints;
vector<Coord> coords;

int findMaxGapIndex() {
    double maxDifference = (coords[1].angle - coords.back().angle) + 360;
    int startingIndex = 1;
    for (size_t i = 1; i < coords.size() - 1; ++i) {
        double difference = coords[i + 1].angle - coords[i].angle;
        if (difference > maxDifference) {
            maxDifference = difference;
            startingIndex = i + 1;
        }
    }
    return startingIndex;
}

bool compareByAngle(const Coord& p1, const Coord& p2) {
    if (abs(p1.angle - p2.angle) > 1e-10)
        return p1.angle < p2.angle;
    long distance1 = (p1.x - coords[0].x) * (p1.x - coords[0].x) + (p1.y - coords[0].y) * (p1.y - coords[0].y);
    long distance2 = (p2.x - coords[0].x) * (p2.x - coords[0].x) + (p2.y - coords[0].y) * (p2.y - coords[0].y);
    return distance1 < distance2;
}

void process() {
    cin >> totalPoints;
    coords.resize(totalPoints);
    for (int i = 0; i < totalPoints; ++i) {
        cin >> coords[i].x >> coords[i].y;
        coords[i].index = i;
        if (i == 0) {
            coords[i].angle = NEGATIVE_INFINITY;
        } else {
            coords[i].angle = atan2(coords[i].y - coords[0].y, coords[i].x - coords[0].x) * 180.0 / PI;
        }
    }
    sort(coords.begin() + 1, coords.end(), compareByAngle);

    cout << totalPoints << "\n1\n";
    int startIdx = findMaxGapIndex();
    for (int i = startIdx; i < totalPoints; ++i) {
        cout << coords[i].index + 1 << "\n";
    }
    for (int i = 1; i < startIdx; ++i) {
        cout << coords[i].index + 1 << "\n";
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    process();
}
