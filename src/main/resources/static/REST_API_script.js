
function openCity(evt, cityName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " active";
}

// Get the element with id="defaultOpen" and click on it
document.getElementById("defaultOpen").click();
document.getElementById("defaultOpen2").click();
function openPanel(evt, cityName) {
    // Declare all variables
    var i, tabcontent, tablinks;

    // Get all elements with class="tabcontent" and hide them
    tabcontent = document.getElementsByClassName("tabcontent2");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    // Get all elements with class="tablinks" and remove the class "active"
    tablinks = document.getElementsByClassName("tablinks2");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    // Show the current tab, and add an "active" class to the button that opened the tab
    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " active";

}
async function getTableWithUsers() {
    let table = document.getElementById('adminTable');
    table.innerHTML = "<tr class=\"userTable\">\n" +
        "                <th class=\"userTable\">ID</th>\n" +
        "                <th class=\"userTable\">First Name</th>\n" +
        "                <th class=\"userTable\">Last Name</th>\n" +
        "                <th class=\"userTable\">Email</th>\n" +
        "                <th class=\"userTable\">Roles</th>\n" +
        "                <th class=\"userTable\">Edit</th>\n" +
        "                <th class=\"userTable\">Delete</th>\n" +
        "            </tr>";
    let response = await fetch('http://localhost:8080/users');
    let users = await response.json();
    let fragment = document.createDocumentFragment();
    users.forEach(function (user) {
        let row = document.createElement('tr');
        row.insertCell(0).innerHTML = user.id;
        row.insertCell(1).innerHTML = user.firstName;
        row.insertCell(2).innerHTML = user.lastName;
        row.insertCell(3).innerHTML = user.email;
        row.insertCell(4).innerHTML = user.roles.map(function(str) {
            return str.length > 5 ? str.substring(5) : str;
        }).join(", ");

        let editButton = document.createElement('button');
        editButton.textContent = 'Edit';
        editButton.addEventListener('click', function() {
            editUser(user.id);
        });
        row.insertCell(5).appendChild(editButton);
        let deleteButton = document.createElement('button');
        deleteButton.textContent = 'Delete';
        deleteButton.addEventListener('click', function() {
            deleteUser(user.id);
        });
        row.insertCell(6).appendChild(deleteButton);
        fragment.appendChild(row);
    });
    table.appendChild(fragment);
}
async function deleteUser(id) {
    let user = await fetch("http://localhost:8080/users/" + id);
    user = await user.json();
    document.getElementById('deleteUserModal').style.display = 'block';
    document.getElementById('deleteUserId').value = id;
    document.getElementById('deleteFirstName').value = user.firstName;
    document.getElementById('deleteLastName').value = user.lastName;
    document.getElementById('deleteEmail').value = user.email;
    document.getElementById("deleteUser").addEventListener('click', async function() {
        let response = await fetch(`http://localhost:8080/users/${id}`, {
            method: 'DELETE'
        });
        if (response.ok) {
            document.getElementById('deleteUserModal').style.display = 'none';
            getTableWithUsers();
        }

    })

}

 async function editUser(id) {
   let user = await fetch('http://localhost:8080/users/' + id);
   user = await user.json();

    document.getElementById('editUserModal').style.display = 'block';
    document.getElementById('editUserId').value = id;
    document.getElementById('editFirstName').value = user.firstName;
    document.getElementById('editLastName').value = user.lastName;
    document.getElementById('editEmail').value = user.email;
    document.getElementById('editPassword').value = "";
     document.getElementById("editUserButton").addEventListener('click',  async function() {
         await editUserConfirm();

     });
}

async function editUserConfirm() {
    let response = await fetch(`http://localhost:8080/users`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: document.getElementById('editUserId').value,
            firstName: document.getElementById('editFirstName').value,
            lastName: document.getElementById('editLastName').value,
            email: document.getElementById('editEmail').value,
            password: document.getElementById('editPassword').value,
            roles: Array.from(document.getElementById('editRoles').selectedOptions).map(option => option.value)
        })
    });
    if (response.ok) {
        document.getElementById('editUserModal').style.display = 'none';
    }
}
async function addUser() {

    const addUserForm = document.getElementById('addUserForm');
    const addFirstName = document.getElementById('addFirstName');
    const addLastName = document.getElementById('addLastName');
    const addEmail = document.getElementById('addEmail');
    const addPassword = document.getElementById('addPassword');


    addUserForm.addEventListener('submit', handleSubmit);

    async function handleSubmit() {
        await fetch(`http://localhost:8080/users`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                firstName: addFirstName.value,
                lastName: addLastName.value,
                email: addEmail.value,
                password: addPassword.value,
                roles: Array.from(document.getElementById('addRoles').selectedOptions).map(option => option.value)
            })
        }).then(getTableWithUsers())


    }
}


