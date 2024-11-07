#define print_var(x) printf(#x " is %d", x )
#define VALUE 40

int main() {
    int v = 20;
    print_var(v);
    print_var(10);
    print_var(VALUE);

    return 0;
}