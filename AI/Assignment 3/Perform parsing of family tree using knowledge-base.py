family = {
    'John': ['Alice', 'Bob'],
    'Bob': ['Carol', 'David']
}

# Rule 1: Parent
def is_parent(x, y):
    return y in family.get(x, [])

# Rule 2: Child
def is_child(x, y):
    return is_parent(y, x)

# Rule 3: Sibling
def is_sibling(x, y):
    for parent, children in family.items():
        if x in children and y in children and x != y:
            return True
    return False

# Rule 4: Grandparent
def is_grandparent(x, y):
    for child in family.get(x, []):
        if y in family.get(child, []):
            return True
    return False

print(is_parent('John', 'Alice'))       # True
print(is_child('Bob', 'John'))          # True
print(is_sibling('Alice', 'Bob'))       # True
print(is_grandparent('John', 'Carol'))  # True
print(is_grandparent('John', 'Carol'))