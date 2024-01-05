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
function fillFormWithData(button,
                          userIdInputId,
                          firstNameInputId,
                          lastNameInputId,
                          emailInputId) {
    let id = button.getAttribute('data-id');
    let firstname = button.getAttribute('data-firstname');
    let lastname = button.getAttribute('data-lastname');
    let email = button.getAttribute('data-email');

    if (id !== null) {
        document.querySelector(userIdInputId).value = id;
    }
    if (firstname !== null) {
        document.querySelector(firstNameInputId).value = firstname;
    }
    if (lastname !== null) {
        document.querySelector(lastNameInputId).value = lastname;
    }
    if (email !== null) {
        document.querySelector(emailInputId).value = email;
    }
}
for (let i = 0; i < document.getElementsByClassName('editButton').length; i++) {
    document.getElementsByClassName('editButton')[i].addEventListener('click', editUser);
}
for (let i = 0; i < document.getElementsByClassName('deleteButton').length; i++) {
    document.getElementsByClassName('deleteButton')[i].addEventListener('click', deleteUser);
}
function editUser () {
    document.getElementById('editUserModal').style.display='block';
    fillFormWithData((this),
        '#editUserId',
        '#editFirstName',
        '#editLastName',
        '#editEmail');
}
function deleteUser () {
    document.getElementById('deleteUserModal').style.display='block';
    fillFormWithData((this),
        '#deleteUserId',
        '#deleteFirstName',
        '#deleteLastName',
        '#deleteEmail');
}

