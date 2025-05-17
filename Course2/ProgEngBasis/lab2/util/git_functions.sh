clear_func(){
  rm -rf src/*
  rm -rf src/.git
  rm -rf src/.svn
  rm -r src
}

commit_func(){
  local name=$1
  local number="${name//[^0-9]/}"
  local author=$2
  local br=$3
  local is_new_br=$4
  local archive="../commits/commit${number}.zip"

  git restore .
  git clean -fd

  if [ "$is_new_br" == true ]; then
    if git rev-parse --verify HEAD >/dev/null 2>&1; then
      git checkout -b "${br}"
    else
      git checkout --orphan "${br}"
    fi
  else
    git checkout "${br}"
  fi

  git restore .
  git clean -fd

  if [ -s "$archive" ]; then
    unzip -o "$archive" -d ./ >/dev/null
  fi

  git add .

  if git diff --name-only --diff-filter=U | grep -q .; then
    for file in $(git diff --name-only --diff-filter=U); do
      git checkout --ours "$file" 2>/dev/null || true
      git add "$file"
    done
  fi

  git commit --allow-empty --author="${author} <${author}@bruh.me>" -m "$name"
}



merge_func(){
  local br_to=$1
  local br_from=$2
  local author=$3
  local name=$4
  local number="${name//[^0-9]/}"
  local archive="../commits/commit${number}.zip"

  git checkout "${br_to}"
  git restore .
  git clean -fd

  if [ -s "$archive" ]; then
    unzip -o "$archive" -d ./ >/dev/null
    git add .
    git commit --allow-empty --author="${author} <${author}@bruh.me>" -m "pre-merge content for $name"
  fi

  git merge --no-commit "${br_from}" -m "merged ${br_from} to ${br_to}"

  if git diff --name-only --diff-filter=U | grep -q .; then
    for file in $(git diff --name-only --diff-filter=U); do
      git checkout --ours "$file" 2>/dev/null || true
      git add "$file"
    done
  fi

  git commit --allow-empty --author="${author} <${author}@bruh.me>" -m "$name"
}

init_func(){
  git init
  git config user.name "red"
  git config user.email "red@bruh.me"
}
