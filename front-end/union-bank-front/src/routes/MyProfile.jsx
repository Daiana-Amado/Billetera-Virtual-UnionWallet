import { Container } from "@mui/material"
import LayoutLoged from "../components/layouts/dawer-sidebar/LayoutLoged"
import { MyProfileComponent } from "../components/profile/MyProfileComponent"
import { useUser } from "../context/UserContext";


export const MyProfile = () => {

  const { user } = useUser();

  return (
    <LayoutLoged>
      <Container sx={{mt:10, }}>
        <MyProfileComponent user={user}/>
      </Container>           
    </LayoutLoged>    
  )
}
