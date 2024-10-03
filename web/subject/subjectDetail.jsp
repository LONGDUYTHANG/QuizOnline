<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

    </head>

    <body data-theme="default" data-layout="fluid" data-sidebar-position="left" data-sidebar-layout="default">
        <div class="wrapper">

            <div class="main">

                <main class="content">
                    <div class="container-fluid p-0">
                        <h1 class="h3 mb-3">Subject Detail 
                            <button class="btn btn-outline-primary">View Lesson</button>
                        </h1>

                        <div class="card">
                            <div class="card-header">
                                <h5 class="card-title">
                                    <button class="btn btn-outline-success active">Overview</button>
                                    <a href="price-package">
                                        <button class="btn btn-outline-success">Price Package</button>

                                    </a>
                                    <a href="dimension">
                                        <button class="btn btn-outline-success">Dimension</button>
                                    </a>  
                                </h5>
                                <h6 class="card-subtitle text-muted"></h6>
                            </div>

                            <div class="card-body">
                                <form action="subject-details" method="post"  enctype="multipart/form-data">
                                    <div class="row">
                                        <!-- First column: col-md-7 -->
                                        <div class="col-md-7">
                                            <div class="card bg-light py-2 py-md-3 border">
                                                <div class="card-body">
                                                    <div>
                                                        <h5>Subject Name</h5>
                                                        <div class="card">
                                                            <div class="card-body">
                                                                <input type="text" name="subjectName" class="form-control" placeholder="Write subject name here..." required>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div>
                                                        <h5>Subject Category</h5>
                                                        <div class="card">
                                                            <div class="card-body">
                                                                <select name="subjectCategory" class="form-control mb-3" required>
                                                                    <option selected disabled>Select subject Category</option>

                                                                    <!-- Assume 'categories' is the list of SubjectCategory objects -->
                                                                    <c:forEach var="category" items="${listCategory}">
                                                                        <option value="${category.category_id}">${category.category_name}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div>
                                                        <div class="row">
                                                            <div class="col-md-4">
                                                                <div class="card bg-light py-2 py-md-3 border">
                                                                    <div class="card-body">
                                                                        <h5>Featured</h5>
                                                                        <div class="form-check form-switch">
                                                                            <label class="form-check-label" for="flexSwitchCheckDefault">Featured subject</label>
                                                                            <input class="form-check-input" type="checkbox" id="flexSwitchCheckDefault" name="featured">
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-8">
                                                                <div class="card bg-light py-2 py-md-3 border">
                                                                    <div class="card-body">
                                                                        <h5>Status</h5>
                                                                        <select name="status" class="form-control mb-3" required>
                                                                            <option selected disabled>Select Status</option>
                                                                            <option value="1">Active</option>
                                                                            <option value="0">Inactive</option>
                                                                        </select>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Second column: col-md-5 -->
                                        <div class="col-md-5 text-center">
                                            <div class="card">
                                                <img id="imagePreview" class="card-img-top" src="img/photos/unsplash-2.jpg" alt="Unsplash">
                                                <div class="card-header">
                                                    <h5 class="card-title mb-0">Image</h5>
                                                </div>
                                                <div class="card-body">
                                                    <input type="file" name="image" class="form-control-file" accept="image/*" required onchange="previewImage(event)">
                                                </div>
                                            </div>
                                        </div>
                                        <script>
                                            function previewImage(event) {
                                                const file = event.target.files[0];
                                                const preview = document.getElementById('imagePreview');

                                                if (file) {
                                                    const reader = new FileReader();

                                                    reader.onload = function (e) {
                                                        preview.src = e.target.result;
                                                    }

                                                    reader.readAsDataURL(file);
                                                }
                                            }
                                        </script>


                                    </div>

                                    <div class="card bg-light py-2 py-md-3 border">
                                        <div class="card-body">
                                            <div>
                                                <h5>Description</h5>
                                                <div class="card">
                                                    <div class="card-body">
                                                        <textarea name="description" class="form-control" rows="2" placeholder="Enter Description" required></textarea>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card bg-light py-2 py-md-3 border">
                                        <div class="card-body">
                                            <div>
                                                <h5>Tag line </h5>
                                                <div class="card">
                                                    <div class="card-body">
                                                        <input type="text" name="tagLine" class="form-control" placeholder="Write tag here..." required>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card bg-light py-2 py-md-3 border">
                                        <div class="card-body">
                                            <div>
                                                <h5>Assigned Expert </h5>
                                                <h4>User 1</h4>
                                                <h5>Email: ...@gmail.com</h5>
                                                <input value="2" name="accountId" hidden="" type="text">
                                            </div>
                                        </div>
                                    </div>

                                    <div>
                                        <button type="button" class="btn btn-pill btn-success" data-bs-toggle="modal" data-bs-target="#confirmationModal">Submit</button>
                                    </div>


                                    <!-- Bootstrap Modal -->
                                    <div class="modal fade" id="confirmationModal" tabindex="-1" aria-labelledby="confirmationModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="confirmationModalLabel">Confirm Changes</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    Are you sure you want to save changes?
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                                    <button type="submit" class="btn btn-primary" id="confirmSubmit">Save Changes</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </main>



            </div>
        </div>

    </body>

</html>
