TYPE=$1

# shellcheck disable=SC1090
source util/"${TYPE}"_functions.sh

clear_func

mkdir src
cd src

init_func

commit_func "r0" "red" "br0" true

commit_func "r1" "blue" "br1" true

commit_func "r2" "blue" "br2" true

commit_func "r3" "blue" "br2" false

commit_func "r4" "blue" "br3" true

commit_func "r5" "blue" "br4" true

commit_func "r6" "blue" "br5" true

commit_func "r7" "blue" "br1" false

merge_func "br2" "br1" "blue" "r8"

commit_func "r9" "blue" "br5" false

commit_func "r10" "red" "br6" true

commit_func "r11" "blue" "br5" false

commit_func "r12" "red" "br6" false

commit_func "r13" "red" "br7" true

merge_func "br5" "br7" "blue" "r14"

commit_func "r15" "blue" "br3" false

commit_func "r16" "red" "br0" false

commit_func "r17" "red" "br6" false

commit_func "r18" "blue" "br4" false

commit_func "r19" "red" "br6" false

commit_func "r20" "blue" "br8" true

commit_func "r21" "blue" "br3" false

commit_func "r22" "red" "br0" false

commit_func "r23" "blue" "br2" false

commit_func "r24" "blue" "br2" false

commit_func "r25" "red" "br0" false

commit_func "r26" "blue" "br5" false

commit_func "r27" "blue" "br5" false

commit_func "r28" "red" "br0" false

commit_func "r29" "blue" "br2" false

commit_func "r30" "blue" "br4" false

commit_func "r31" "red" "br6" false

commit_func "r32" "blue" "br8" false

commit_func "r33" "red" "br0" false

commit_func "r34" "blue" "br2" false

commit_func "r35" "red" "br0" false

commit_func "r36" "blue" "br5" false

commit_func "r37" "red" "br6" false

commit_func "r38" "red" "br6" false

commit_func "r39" "blue" "br3" false

merge_func "br2" "br3" "blue" "r40"

merge_func "br8" "br2" "blue" "r41"

merge_func "br4" "br8" "blue" "r42"

commit_func "r43" "blue" "br9" true

commit_func "r44" "blue" "br4" false

merge_func "br5" "br4" "blue" "r45"

commit_func "r46" "blue" "br5" false

merge_func "br0" "br5" "red" "r47"

commit_func "r48" "red" "br6" false

merge_func "br9" "br6" "blue" "r49"

merge_func "br0" "br9" "red" "r50"