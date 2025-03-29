import React, { useEffect, useState } from "react"; 
import axios from "axios";
import { Card, CardContent, Button, Input, Box, Typography, Grid } from "@mui/material";
import { styled } from "@mui/system";

// Custom styled components using MUI's styled API
const Root = styled(Box)(({ theme }) => ({
  backgroundColor: "#f7f9fc", // Light background color for a clean look
  padding: theme.spacing(4),
  borderRadius: "10px",
  boxShadow: "0 4px 10px rgba(0, 0, 0, 0.1)",
  maxWidth: "900px",
  margin: "auto",
}));

const Title = styled(Typography)(({ theme }) => ({
  fontSize: "2rem",
  fontWeight: "600",
  marginBottom: theme.spacing(3),
  color: "#333",
  textAlign: "center",
}));

const StyledCard = styled(Card)(({ theme }) => ({
  backgroundColor: "#fff",
  boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)",
  borderRadius: "8px",
  display: "flex",
  justifyContent: "space-between",
  alignItems: "center",
  padding: theme.spacing(2),
  marginBottom: theme.spacing(2),
  transition: "transform 0.3s ease",
  "&:hover": {
    transform: "scale(1.05)",
  },
}));

const AddButton = styled(Button)(({ theme }) => ({
  backgroundColor: "#1976d2", // Primary color
  color: "#fff",
  "&:hover": {
    backgroundColor: "#1565c0", // Darker on hover
  },
}));

const DeleteButton = styled(Button)(({ theme }) => ({
  backgroundColor: "#d32f2f", // Red color for delete
  color: "#fff",
  "&:hover": {
    backgroundColor: "#b71c1c", // Darker red on hover
  },
}));

const StyledInput = styled(Input)(({ theme }) => ({
  width: "100%",
  marginBottom: theme.spacing(2),
  padding: theme.spacing(1.5),
  borderRadius: "5px",
  border: "1px solid #ddd",
}));

const WasteSortingApp = () => {
  const [categories, setCategories] = useState([]);
  const [newCategory, setNewCategory] = useState("");
  const [searchId, setSearchId] = useState(""); // To store the searched ID
  const API_URL = "http://localhost:8080/api/waste-categories"; // Adjust your backend URL if needed.

  // Fetch all categories on component mount
  useEffect(() => {
    fetchCategories();
  }, []);

  // Fetch all categories from the API
  const fetchCategories = async () => {
    try {
      const response = await axios.get(API_URL);
      setCategories(response.data);
    } catch (error) {
      console.error("Error fetching categories", error);
    }
  };

  // Add a new category
  const addCategory = async () => {
    if (!newCategory.trim()) return; // Skip if input is empty
    try {
      await axios.post(API_URL, { name: newCategory.trim() });
      setNewCategory(""); // Clear input field after adding
      fetchCategories(); // Re-fetch categories to update the list
    } catch (error) {
      console.error("Error adding category", error);
    }
  };

  // Delete a category by its ID
  const deleteCategory = async (id) => {
    try {
      await axios.delete(`${API_URL}/${id}`);
      fetchCategories(); // Re-fetch categories to update the list
    } catch (error) {
      console.error("Error deleting category", error);
    }
  };

  // Filter categories based on the search ID
  const filteredCategories = categories.filter((category) =>
    category.id.toString().includes(searchId)
  );

  return (
    <Root>
      <Title variant="h4">Waste Sorting Categories</Title>

      {/* Search section for category ID */}
      <Box display="flex" justifyContent="center" mb={3}>
        <StyledInput
          type="text"
          placeholder="Search by category ID"
          value={searchId}
          onChange={(e) => setSearchId(e.target.value)} // Update search term
        />
      </Box>

      {/* Input section for adding a new category */}
      <Box display="flex" gap={2} mb={4}>
        <StyledInput
          type="text"
          placeholder="Enter category name"
          value={newCategory}
          onChange={(e) => setNewCategory(e.target.value)}
        />
        <AddButton variant="contained" onClick={addCategory}>
          Add Category
        </AddButton>
      </Box>

      {/* Display the list of categories */}
      <Grid container spacing={2}>
        {filteredCategories.length > 0 ? (
          filteredCategories.map((category) => (
            <Grid item xs={12} sm={6} md={4} key={category.id}>
              <StyledCard>
                <CardContent>
                  <Typography variant="h6">
                    <strong>ID:</strong> {category.id}
                  </Typography>
                  <Typography variant="body1">
                    <strong>Name:</strong> {category.name}
                  </Typography>
                </CardContent>
                <DeleteButton
                  variant="contained"
                  onClick={() => deleteCategory(category.id)}
                >
                  Delete
                </DeleteButton>
              </StyledCard>
            </Grid>
          ))
        ) : (
          <Typography color="textSecondary" align="center">
            No categories available or matching the search.
          </Typography>
        )}
      </Grid>
    </Root>
  );
};

export default WasteSortingApp;
