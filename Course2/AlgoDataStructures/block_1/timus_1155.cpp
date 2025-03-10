#include <iostream>
using namespace std;

void printMoves(const string& moves) {
    cout << moves;
}

int main() {
    int a, b, c, d, e, f, g, h;
    cin >> a >> b >> c >> d >> e >> f >> g >> h;

    if ((f + h + a + c) != (d + g + e + b)) {
        cout << "IMPOSSIBLE" << endl;
        return 0;
    }

    for (int i = 0; i < 100; i++) {
        if (a > 0 && b > 0) { printMoves("AB-\n"); a--; b--; }
        if (a > 0 && d > 0) { printMoves("AD-\n"); a--; d--; }
        if (a > 0 && e > 0) { printMoves("AE-\n"); a--; e--; }
        if (a > 0 && g > 0) { printMoves("FB+\nFG-\nAB-\n"); a--; g--; }

        if (h > 0 && e > 0) { printMoves("HE-\n"); h--; e--; }
        if (h > 0 && d > 0) { printMoves("HD-\n"); h--; d--; }
        if (h > 0 && g > 0) { printMoves("HG-\n"); h--; g--; }
        if (h > 0 && b > 0) { printMoves("FG+\nHG-\nFB-\n"); h--; b--; }

        if (f > 0 && b > 0) { printMoves("FB-\n"); f--; b--; }
        if (f > 0 && g > 0) { printMoves("FG-\n"); f--; g--; }
        if (f > 0 && e > 0) { printMoves("FE-\n"); f--; e--; }
        if (f > 0 && d > 0) { printMoves("HG+\nHD-\nFG-\n"); f--; d--; }

        if (c > 0 && b > 0) { printMoves("CB-\n"); c--; b--; }
        if (c > 0 && d > 0) { printMoves("CD-\n"); c--; d--; }
        if (c > 0 && g > 0) { printMoves("CG-\n"); c--; g--; }
        if (c > 0 && e > 0) { printMoves("HG+\nEH-\nCG-\n"); c--; e--; }
    }

    return 0;
}
