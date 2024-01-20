async function fillHeader() {
    let user = await fetch('http://localhost:8080/users/user');
    user = await user.json();
    document.getElementsByClassName('headerForUserFirst')[0].textContent = "User " + user.email + " with roles: " + user.roles.map(role => role.substring(5)).join(', ');
    await fillTable(user);
}

async function fillTable(user) {

    let table = document.getElementById('userTable');
    if (table.rows.length > 1) {
        table.deleteRow(1);
    }
    let row = document.createElement('tr');

    let cell0 = document.createElement('td');

    cell0.textContent = user.id;
    let cell1 = document.createElement('td');

    cell1.textContent = user.firstName;
    let cell2 = document.createElement('td');

    cell2.textContent = user.lastName;
    let cell3 = document.createElement('td');

    cell3.textContent = user.email;
    let cell4 = document.createElement('td');

    cell4.textContent = user.roles.map(role => role.substring(5)).join(', ');

    row.insertCell().appendChild(cell0);
    row.insertCell().appendChild(cell1);
    row.insertCell().appendChild(cell2);
    row.insertCell().appendChild(cell3);
    row.insertCell().appendChild(cell4);
    table.appendChild(row);
}

fillHeader();