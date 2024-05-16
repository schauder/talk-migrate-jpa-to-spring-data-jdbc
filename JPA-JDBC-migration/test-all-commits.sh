#!/bin/bash

# Check if both arguments are provided
if [ $# -ne 2 ]; then
    echo "Usage: $0 <branch_A> <commit_B>"
    exit 1
fi

# Assign arguments to variables
branch_A=$1
commit_B=$2

# Get list of commits between commit B and branch A
commits=$(git log --format='%H' --reverse $commit_B..$branch_A)

# Iterate through each commit and run tests
for commit in $commits; do
    echo "Running tests for commit: $commit"
    # Checkout the commit
    git checkout $commit > /dev/null 2>&1

    # Run tests
    ./mvnw clean verify > /dev/null 2>&1

    # Check if tests passed
    if [ $? -eq 0 ]; then
        echo "Tests passed for commit: $commit"
    else
        echo "Tests failed for commit: $commit"
        exit 1
    fi
done

# Checkout back to the original branch
git checkout $branch_A > /dev/null 2>&1

echo "All tests passed for commits in branch $branch_A that have commit $commit_B in their history."
