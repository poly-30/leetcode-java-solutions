# The path to your README.md file
readme_path = 'README.md'
# The text to look for to identify the line to update
count_marker = "Total Solved:"

# Read the content of the README file
try:
    with open(readme_path, 'r', encoding='utf-8') as f:
        lines = f.readlines()
except FileNotFoundError:
    print(f"Error: The file {readme_path} was not found.")
    exit(1)

# Count the number of solution rows
solution_count = 0
for line in lines:
    # A line is a solution if it starts with '|' after trimming whitespace,
    # and is not the header or separator line.
    clean_line = line.strip()
    if clean_line.startswith('|') and '---' not in clean_line and 'problem number' not in clean_line.lower():
        solution_count += 1

# Find and update the line with the total count
updated_lines = []
found_marker = False
for line in lines:
    # Use strip() here to handle potential whitespace around the marker
    if line.strip().startswith(count_marker):
        updated_lines.append(f"{count_marker} {solution_count}\n")
        found_marker = True
    else:
        updated_lines.append(line)

if not found_marker:
    print(f"Error: Marker '{count_marker}' not found in README.md.")
    exit(1)

# Write the updated content back to the README file
with open(readme_path, 'w', encoding='utf-8') as f:
    f.writelines(updated_lines)

print(f"✅ README updated successfully with a total of {solution_count} solutions.")