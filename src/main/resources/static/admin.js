$(document).ready(function () {
    createAdminTable();
});

async function createAdminTable() {
    let usersTable = $("#adminTable")
    usersTable.empty()
    let users = await fetch('/api/admin/users').then(response => response.json())
    for (let i = 0; i < users.length; i++) {

        let listRoles = '';
        for (let element of users[i].authorities) {
            listRoles += " " + element.role;
        }
        let userid = users[i].id;
        let username = users[i].name;
        let userlastname = users[i].lastName;
        let userage = users[i].age;
        let useremail = users[i].email;
        let userroles = listRoles;

        let tr = $("<tr>").attr("id", i);
        tr.append(`<td>${userid}</td>
            <td>${username}</td>
            <td>${userlastname}</td>
            <td>${userage}</td>
            <td>${useremail}</td>
            <td>${userroles}</td>

            <td><button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#editModal"
                data-userid="${users[i].id}"
                data-userfirstname="${users[i].name}"
                data-userlastname="${users[i].lastName}"
                data-userage="${users[i].age}"
                data-useremail="${users[i].email}">Edit</button></td>
            <td><button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#delModal"
                data-userid="${users[i].id}"
                data-userfirstname="${users[i].name}"
                data-userlastname="${users[i].lastName}"
                data-userage="${users[i].age}"
                data-useremail="${users[i].email}">Delete</button></td>`
        );
        $(usersTable).append(tr)
    }
}

$('#editModal').on('show.bs.modal', function (event) {
    const button = $(event.relatedTarget)
    const modal = $(this)

    modal.find('#id').val(button.data('userid'))
    modal.find('#name').val(button.data('userfirstname'))
    modal.find('#last_name').val(button.data('userlastname'))
    modal.find('#age').val(button.data('userage'))
    modal.find('#email').val(button.data('useremail'))
})

$('#delModal').on('show.bs.modal', function (event) {
    const button = $(event.relatedTarget)
    const modal = $(this)

    modal.find('#id2').val(button.data('userid'))
    modal.find('#firstName2').val(button.data('userfirstname'))
    modal.find('#lastName2').val(button.data('userlastname'))
    modal.find('#age2').val(button.data('userage'))
    modal.find('#email2').val(button.data('useremail'))
})


$("#buttonSubmitNew").on('click', (event) => {
    event.preventDefault()

    let role = [];
    let ids = Array.from(document.getElementById("newRole").options).filter(option => option.selected).map(option => option.value)
    for (let i = 0; i < ids.length; i++) {
        role.push({id: ids[i]})
    }

    let user = {
        name: $("#newName").val(),
        lastName: $("#newLastName").val(),
        age: $("#newAge").val(),
        email: $("#newEmail").val(),
        password: $("#newPassword").val(),
        roles: role
    }

    fetch('/api/admin/user/new',
        {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(user)
        })
        .then(function () {
            $('#nav-home-tab').tab('show');
            createAdminTable()
        })
})

$("#buttonSubmitEdit").on('click', (event) => {
    event.preventDefault()

    let role = [];
    let ids = Array.from(document.getElementById("role").options).filter(option => option.selected).map(option => option.value)
    for (let i = 0; i < ids.length; i++) {
        role.push({id: ids[i]})
    }

    let editedUser = {
        id: $("#id").val(),
        name: $("#name").val(),
        lastName: $("#last_name").val(),
        age: $("#age").val(),
        email: $("#email").val(),
        password: $("#password").val(),
        roles: role
    }

    fetch(`/api/admin/user`,
        {
            method: 'PUT',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(editedUser)
        })
    setTimeout(() => createAdminTable(), 200);
})

$("#buttonSubmitDelete").on('click', (event) => {
    let id = $("#id2").val();
    event.preventDefault()
    fetch(`/api/admin/user/${id}`,
        {method: `DELETE`});
    setTimeout(() => createAdminTable(), 200);
})
