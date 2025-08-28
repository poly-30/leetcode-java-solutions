# The path to your README.md file
readme_path = 'README.md'

print("--- Starting Diagnostic ---")

# Try to read the file
try:
    with open(readme_path, 'r', encoding='utf-8') as f:
        lines = f.readlines()
    print(f"Successfully read {len(lines)} lines from {readme_path}")
except FileNotFoundError:
    print(f"Error: The file {readme_path} was not found.")
    exit(1)

# Analyze each line
solution_count = 0
for i, line in enumerate(lines):
    # This is the logic we are testing
    clean_line = line.strip()
    is_solution_row = (
        clean_line.startswith('|') and
        '---' not in clean_line and
        'problem number' not in clean_line.lower()
    )

    # Print analysis for lines that look like they could be table rows
    if '|' in line:
        print("\n--------------------------")
        print(f"Line #{i+1}: '{line.strip()}'") # Print the cleaned line in quotes to see whitespace
        print(f"Does it start with '|'? -> {clean_line.startswith('|')}")

        if clean_line:
            # Print the character code of the first character. A normal pipe '|' is 124.
            first_char_code = ord(clean_line[0])
            print(f"ASCII/Unicode code of first character: {first_char_code} (A normal '|' is 124)")

        print(f"Is it a solution row? -> {is_solution_row}")

        if is_solution_row:
            solution_count += 1

print("\n--- Diagnostic Complete ---")
print(f"Final calculated count: {solution_count}")