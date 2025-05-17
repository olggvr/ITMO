#include <ios>
#include <iostream>
#include <vector>

bool compare(const std::string& a, const std::string& b){
    return a + b > b + a;
}

void solve() {
    std::vector<std::string> parts;
    std::string line;
    while (std::cin >> line) {
        parts.push_back(line);
        size_t size = parts.size();
        if (size > 1) {
            for (size_t i = size - 1; i > 0; i--) {
                if (compare(parts[i], parts[i-1])) std::swap(parts[i], parts[i-1]);
            }
        }
    }
    for (const auto& part : parts) std::cout << part;
}
int main() {
    std::ios_base::sync_with_stdio(false); std::cin.tie(nullptr);
    int t = 1;
    while (t--) {
        solve();
    }
}
