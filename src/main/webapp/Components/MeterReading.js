$(document).ready(function() {
	$("#alertSuccess").hide();
	$("#alertError").hide();
});

// save
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
 	$("#alertSuccess").text("");
 	$("#alertSuccess").hide();
 	$("#alertError").text("");
 	$("#alertError").hide();

	// Form validation-------------------
	var status = validateItemForm();
	if (status != true)
 	{
 		$("#alertError").text(status);
 		$("#alertError").show();
 		return;
 	}
	
	// If valid------------------------
	var type = ($("#hidUserIDSave").val() == "") ? "POST" : "PUT";
 	$.ajax(
 	{
 		url : "meterreadingAPI",
 		type : type,
 		data : $("#formItem").serialize(),
 		dataType : "text",
 		complete : function(response, status)
 	{
 
	onItemSaveComplete(response.responseText, status);
 	}
 });
	
	
});
	
// update	
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidUserIDSave").val(
					$(this).closest("tr").find('#hidUserIDUpdate').val());
 	$("#UserID").val($(this).closest("tr").find('td:eq(0)').text());
	$("#FullName").val($(this).closest("tr").find('td:eq(1)').text());
 	$("#City").val($(this).closest("tr").find('td:eq(2)').text());
 	$("#MobileNumber").val($(this).closest("tr").find('td:eq(3)').text());
	$("#Unit").val($(this).closest("tr").find('td:eq(4)').text());
	$("#Remark").val($(this).closest("tr").find('td:eq(5)').text());
});

$(document).on("click", ".btnRemove", function(event)
{
 	$.ajax(
 	{
 		url : "meterreadingAPI",
 		type : "DELETE",
 		data : "id=" + $(this).data("billid"),
 		dataType : "text",
 		complete : function(response, status)
 		{
 		onItemDeleteComplete(response.responseText, status);
 		}
 	});
});


// REMOVE==========================================
$(document).on("click", ".remove", function(event)
{
 	$(this).closest(".meterreading").remove();

 	$("#alertSuccess").text("Removed successfully.");
 	$("#alertSuccess").show();
});



function onItemSaveComplete(response, status)
	{
	if (status == "success")
	 	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} 
		else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} 
	else if (status == "error")
	{
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} 
	else
	{
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	 	$("#hidUserIDSave").val("");
	 	$("#formItem")[0].reset();
	}
	
function onItemDeleteComplete(response, status)
{
	if (status == "success")
 	{
 		var resultSet = JSON.parse(response);
 		if (resultSet.status.trim() == "success")
 	{
 		$("#alertSuccess").text("Successfully deleted.");
 		$("#alertSuccess").show();
 		$("#divItemsGrid").html(resultSet.data);
 	} 
	else if (resultSet.status.trim() == "error")
 	{
 		$("#alertError").text(resultSet.data);
 		$("#alertError").show();
 	}
 	} 
		else if (status == "error")
 	{
 		$("#alertError").text("Error while deleting.");
 		$("#alertError").show();
 	} 
		else
 	{
 		$("#alertError").text("Unknown error while deleting..");
 		$("#alertError").show();
 	}
}

function validateItemForm()
{
	// 	User ID
	if ($("#UserID").val().trim() == "")
 	{
 		return "Insert User ID.";
 	}
	// Full Name
	if ($("#FullName").val().trim() == "")
 	{
 		return "Insert Full Name.";
 	}
	// City
	if ($("#City").val().trim() == "")
	{
 		return "Insert City.";
 	}
	// Mobile Number
	if ($("#MobileNumber").val().trim() == "")
 	{
 		return "Insert Mobile Number.";
 	}
	// Unit
	if ($("#Unit").val().trim() == "")
	{
 		return "Insert Unit.";
 	}
 	//Remark
	if ($("#Remark").val().trim() == "")
	{
 		return "Insert Remark.";
 	}

	return true;
}
