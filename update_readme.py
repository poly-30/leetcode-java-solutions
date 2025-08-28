import re

# The path to your README.md file
readme_path = 'README.md'
# The text to look for to identify the line to update
count_marker = "#### Total Solved:"
# The regex pattern to identify a solution row in the table
# This looks for lines starting with '|' and containing a number in the first column
solution_row_pattern = re.compile(r'\|\s*\d+\s*\|')

# Read the content of the README file
with open(readme_path, 'r', encoding='utf-8') as f:
    lines = f.readlines()

# Count the number of solution rows
solution_count = 0
for line in lines:
    if solution_row_pattern.match(line):
        solution_count += 1

# Find and update the line with the total count
updated_lines = []
found_marker = False
for line in lines:
    if line.startswith(count_marker):
        updated_lines.append(f"{count_marker} {solution_count}\n")
        found_marker = True
    else:
        updated_lines.append(line)

# If the marker line wasn't found, we can't update.
# (This is a safeguard, but it should be there from Step 1)
if not found_marker:
    print(f"Error: Marker '{count_marker}' not found in README.md.")
    exit(1)

# Write the updated content back to the README file
with open(readme_path, 'w', encoding='utf-8') as f:
    f.writelines(updated_lines)

print(f"✅ README updated successfully with a total of {solution_count} solutions.")