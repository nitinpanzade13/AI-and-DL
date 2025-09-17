from collections import defaultdict

# GENDER
male = {'Raj', 'Amit', 'Rohit', 'Suresh', 'Aryan', 'Kiran', 'Rahul', 'Arjun'}
female = {'Sita', 'Neha', 'Priya', 'Anjali', 'Meera', 'Isha', 'Tanya'}

# PARENTS
parents = {
    'Raj': ['Amit', 'Neha', 'Rohit'],
    'Sita': ['Amit', 'Neha', 'Rohit'],
    'Amit': ['Aryan', 'Meera'],
    'Priya': ['Aryan', 'Meera'],
    'Neha': ['Kiran', 'Isha'],
    'Suresh': ['Kiran', 'Isha'],
    'Rohit': ['Rahul', 'Tanya', 'Arjun'],
    'Anjali': ['Rahul', 'Tanya', 'Arjun'],
}


# Create child → parents mapping
children = defaultdict(list)
for parent, kids in parents.items():
    for child in kids:
        children[child].append(parent)

# BASIC CHECKS
def is_male(x): return x in male
def is_female(x): return x in female

def is_parent(p, c): return c in parents.get(p, [])
def is_child(c, p): return is_parent(p, c)

def father(f, c): return is_male(f) and is_parent(f, c)
def mother(m, c): return is_female(m) and is_parent(m, c)

def siblings(x):
    sibs = set()
    for parent in children[x]:
        sibs.update(parents[parent])
    sibs.discard(x)
    return list(sibs)

def brother(x, y):
    return is_male(x) and x in siblings(y)

def sister(x, y):
    return is_female(x) and x in siblings(y)

def grandfather(gp, x):
    return is_male(gp) and gp in grandparents(x)

def grandmother(gp, x):
    return is_female(gp) and gp in grandparents(x)

def grandparents(x):
    gp = set()
    for p in children[x]:
        gp.update(children[p])
    return list(gp)

def uncle(x):
    uncles = set()
    for p in children[x]:
        for sib in siblings(p):
            if is_male(sib):
                uncles.add(sib)
    return list(uncles)

def aunt(x):
    aunts = set()
    for p in children[x]:
        for sib in siblings(p):
            if is_female(sib):
                aunts.add(sib)
    return list(aunts)

def cousins(x):
    cous = set()
    for p in children[x]:
        for sib in siblings(p):
            cous.update(parents.get(sib, []))
    cous.difference_update(parents.get(x, []))
    return list(cous)

print("Grandfather of Meera:", [gp for gp in grandparents('Meera') if is_male(gp)])
print("Grandmother of Meera:", [gp for gp in grandparents('Meera') if is_female(gp)])

print("Siblings of Rohit:", siblings('Rohit'))

print("Brothers of Neha:", [b for b in siblings('Neha') if is_male(b)])
print("Sisters of Rohit:", [s for s in siblings('Rohit') if is_female(s)])

print("Father of Aryan:", [p for p in children['Aryan'] if is_male(p)])
print("Mother of Aryan:", [p for p in children['Aryan'] if is_female(p)])

print("Uncle of Tanya:", uncle('Tanya'))
print("Aunt of Meera:", aunt('Meera'))

print("Cousins of Kiran:", cousins('Kiran'))
