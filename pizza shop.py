orderTotal = 0.0
print("Welcome to our pizza shop")
name = input("What is your name?\n")
pizza_Selection = ["Cheese Pizza", "Veggie Pizza", "Gluten-Free Pizza"]
print(pizza_Selection)
pizza = input("Which pizza would you like to order?\n")

size_Selection = ["Large", "Medium", "Small"]
print(size_Selection)
size = input("What size?\n")

if pizza == pizza_Selection[0]:
    orderTotal += 7.99
    if size == size_Selection[0]:
        print("That would be {0:.2f} !".format(orderTotal))

    if size == size_Selection[1]:
        orderTotal *= 0.9
        print("That would be {0:.2f} !".format(orderTotal))

    if size == size_Selection[2]:
        orderTotal *= 0.8
        print("That would be {0:.2f} !".format(orderTotal))

if pizza == pizza_Selection[1]:
    orderTotal += 8.99
    if size == size_Selection[0]:
        print("That would be {0:.2f} !".format(orderTotal))

    if size == size_Selection[1]:
        orderTotal *= 0.9
        print("That would be {0:.2f} !".format(orderTotal))

    if size == size_Selection[2]:
        orderTotal *= 0.8
        print("That would be {0:.2f} !".format(orderTotal))

if pizza == pizza_Selection[2]:
    orderTotal += 10.99
    if size == size_Selection[0]:
        print("That would be {0:.2f} !".format(orderTotal))

    if size == size_Selection[1]:
        orderTotal *= 0.9
        print("That would be {0:.2f} !".format(orderTotal))

    if size == size_Selection[2]:
        orderTotal *= 0.8
        print("That would be {0:.2f} !".format(orderTotal))

spice_Selection = ["Mild", "Medium", "Hot"]
print(spice_Selection)
spice = input("How spicy would you like your pizza to be\n")
if spice == spice_Selection[0]:
    print("Sounds Great")
if spice == spice_Selection[1]:
    print("Sounds Great")
if spice == spice_Selection[2]:
    print("Sounds Great")
drink_Selection = ["Pepsi", "Root-Beer", "Coke", "Diet-Coke", "Mountain-Dew", "None"]
print(drink_Selection)
print("All drinks are 2L bottles for $3.50")
drink = input("Choose a drink from the following!\n")
orderTotal += 3.50
if drink == drink_Selection[0]:
    print("Good Choice")
if drink == drink_Selection[1]:
    print("Good Choice")
if drink == drink_Selection[2]:
    print("Good Choice")
if drink == drink_Selection[3]:
    print("Good Choice")
if drink == drink_Selection[4]:
    print("Good Choice")
if drink == drink_Selection[5]:
    print("Good Choice")
    orderTotal -= 3.50
delivery_Carryout_one = ["Delivery / Carryout"]
print(delivery_Carryout_one)
print("(DISCLAIMER)Delivery is $20.00 due to the corona virus!!!")
method = input("Delivery or Carryout\n")
if method == str("Delivery"):
    print("Sounds Good")
    orderTotal += 20.00
if method == str("Carryout"):
    print("Sounds Good")

tip_Selection = input("Would you like to add a tip? yes/no\n")
tip_amount = 0
if(tip_Selection == "yes"):
    tip_amount = int(input("How Much?\n"))
orderTotal += tip_amount
pay = ['Cash or Debit']
print(pay)
paypal = input("How would you like to pay?\n")
if paypal == str("Cash"):
    print("Ok")
if paypal == str("Debit"):
    print("Ok")
total = int(orderTotal)
txt = "\t\t~~~~~~~~~~~~~~~~~~~~\n\t\tThe total is ${total:.2f}\n\t\t~~~~~~~~~~~~~~~~~~~~"
print(txt.format(total=orderTotal))

coupon = ["JHG543JH78MN"]
print(coupon)
print("Use this coupon to get a free drink on your next order")
print("Coupon is valid for one time use only!")
print("Enjoy your order:)")


