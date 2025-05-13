import React from "react";
import './Profile.css';



interface IProfileProps {
    name: string;
    imageUrl: string;
}
export interface IUserData {
    name: string;
    email: string;
    imageUrl: string;
}

const Profile: React.FC<IProfileProps> = ({name, imageUrl}) => {



    return (
        <div className="profile-container">
            <div className="container">
                <div className="profile-info">
                    <div className="profile-avatar">
                        {
                            imageUrl ? (
                                <img src={imageUrl} alt={name}/>
                            ) : (
                                <div className="text-avatar">
                                    <span>{name}</span>
                                </div>
                            )
                        }
                    </div>
                    <div className="profile-name">
                        <h2>{name}</h2>
                        <h2>test profle user</h2>
                    </div>
                </div>
            </div>
        </div>
    )
};

export default Profile;