	� G %/accs/floating_arms/floating_arms.mot ��       �W[oG~�S#�u�ٵ;-r�BH"BHUe��cg�����ԆF�:�P)�FA��"P)xJ�KW6�S�B����$i#�PU�bmvϜ���̙^��f�AU%#9D�biĨ��jk5�߻G3j�MЂE,[��
Q��-�*���ȕF��ؖ����Z&L�}b�(�h@��hie4�ɳ{��8�����c���N�9qn^)[_���#ꖺ0I����]�"�O�ZSGH�R;sF��rA3����W~�Ԝ�]�M��آ�c��&��C��!U÷}�c�Z���	YQUG��B4�ZTlÑ�y�"SE����H�D�`�$�a,�,{�uo冷��[Y�\v=����o�9����V4gۋ��,�='�q�8�@%Ff]��q���!p�h:�X�d����Ȃ��9ׇ�TC���/."����%��-�FM�ʸ؀7��	N��H�1�x\߂��~��U���.����wrޏ��E!�2�!���B�V�J���MX��E\4n�Z����v�^tV��/������O񈎁�:߹[!�R� �ʳ���J�R�*C�4�Od4����S��S/	F�}��{�sox�eϽ��.x+��ʏ��%��=T
ުd^a@W�A)W�Yj���������/)1=(�����I�6����d�i�}��ӵ����$m��^�(�+W=�[�u�}�N��ȫ�fg��c
��lذee����+DX��v�4�6� ���/�@�����ǩd`�����sݱ��,���@�t�U��3�t`hQ(��1��L������4Ff�~��a�)%���G�n�2э3"jB� �\�,���9������rC�<��ƽ[���g]�m�֓����zY��MϽD��>�ܯ�֪����<���ݬh��C���|lqQ�5N�n�/0���9��r6�4����u��tC;gl�������K����|����8<�������T��:�jۤ;ն�����}N����I��e�|g�I��K�e��@4����������A2�z�Kl+��/�PP�83	��ʜ<M7֛+��9��г�e�p*���4}����.���{�/��e�㙊b�H��9 "e�������Xbg8��'��]�Q�e��?�g7n�xD���p��d��#��c�u����n�;޵R�n�AOK���e�Jp(�ןv֮s�tw���v��a�V�]�r�#.I�$6�G~��F��4�q�E����Ri*!f}�k�Ҝc�+��(8G��5B��. W���hb� S'fr[0�Q��AWr�n��u�N>á�NTGJH摣*0�f��%�Ӳb�4J��{^#�Z���K8P�
P9�4l��*}N�6L�.�гe�P�-�M/��@� >u�1f?l�ݔ�m&�^�Jš���e���}'��%��-2��� N��ʶ��J]�;Mb�Ň/>������ -�}�@l"|o G�+���W^�"W��ҽ�#D/I[Q��u�>|���Z2�"�Ђ����Ӛ���&���N�(�#�A (0�%.@u�T�>��P��� $��T"��/>�R9�V�N)�Jj�#�p�Qs.9= �#�Ru
x�U`C#�&�&���#��c��щ��Q�K�Me��+�N����M��*���	��p����� ��Y���   �  Lga2/stage/AmpedScript; e start floating_arms_explode_x_execute 
shotCalc_x (Lkotori/geom/Vector3D;)V shot_x (Lkotori/geom/Quaternion;)V broken broken_a broken_x 
setVisible (Z)V
 f p q setShadowState
 d s 3 lockItemScript (ZZ)V
 f u v
 H 0 3 lockTimeCount
 f y q finishAllConnections
 d { 1 clearGenerate
 d } 1 generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d  � setGenerate 8(Lkotori/gfx/KGLScriptModel;)Lkotori/gfx/KGLScriptModel;
 d � �D "/accs/floating_arms/shootL_sx1.scm�            ?�  ?�  ?�               start2 isServer ()Z
 f � � getNearestEnemyPos (I)[Lkotori/geom/Vector3D;
 f � � '(Ljava/lang/Object;I)Ljava/lang/Object;
 H * � 
itemScript ((Ljava/lang/String;ILjava/lang/Object;)V
 f � � kill
 f � 1 
stopScript
 f � 1 getArgv ()Ljava/lang/Object;
 f � � getGenerate (I)Lkotori/gfx/KGLScriptModel;
 d � � getPosition ()Lkotori/geom/Position;
 f � �  sub .(Lkotori/geom/Vector3D;)Lkotori/geom/Vector3D;
 � � � length ()F
 � � �@�  �� kotori.geom.Quaternion?}�5>�e         Lkotori/geom/KMath; � getDir (Lkotori/geom/Vector3D;)F
 � � � R2D (F)F
 � � � Ljava/lang/Math; � round (F)I
 � � �
 f � �C4  C�  �4  	 � R  (FF)F
 � � � min
 � � �B4  A�  ��?�               Lkotori/geom/Quaternion; � clone
 � � �=��� getHPBQuaternionD 5(FFFLkotori/geom/Quaternion;)Lkotori/geom/Quaternion;
 � � � Lkotori/gfx/KGLScriptModel; � rotate (Lkotori/geom/Quaternion;I)V
 � � ���?}�5>�e         startScript (Ljava/lang/String;)Z
 � � � beam2 vibe (FFFFI)V
 d � �<#�
 	playSound
 d � 3���D /efx/gun/shell_floating_x.scm�        ����@�  @�  @�              � hit_be  d� �    a��       ,�>�         	genoffset 1(Lkotori/geom/Vector3D;Lkotori/geom/Quaternion;)V
 d � � attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-             beam_end
 � � 1D /accs/floating_arms/arms.scm�            ?�  ?�  ?�                getParentPlayerModel ()Lga2/stage/PlayerModel;
 f � � Lkotori/data/KGLGenerationData; � pos (FFF)V
 � � � back2_L putParam '(Ljava/lang/String;Ljava/lang/Object;)V
 � � � bc_attacker setFlagsToModifier (ILjava/lang/String;I)V
 f � � floating_arms    efx_endD /accs/floating_arms/arms.scm�            ?�  ?�  ?�                f  g 1  }*� rW*� tW*� wW*d� xW*� wW*� zW*� |W*� ~W**��� �� �W* ȶ xW*� ��  L*� �M,� 
*,� �L*h+� �W*� xW*� �W*� �W�  h 1  *� �L*� xW*+� W*d� xW*� W�  i j �*� �M,��+��*� �N+-� �W+� �8��� *,� xW*�� W�+� �� �� ��8*� �� �� ��8��� 
�f8��� 
�f8f8��� �f8� ��� 
�b8+� �8+� �+� �8		� �� �� �Zd�8

�� #b
j8b
j�
� ��nj8b8

�f8�:� �:6� /�`�jj8� �W,� �W*
� xW�W
���6� 0�`�jj8� �W,� �W*
� xW�W
���*d� xW� �W,� �W
� �W*� W� *,� xW*�� W�  k l  p*� �M,� 
,Ҷ �W*^� xW*�\�� �W* ϶ �W�N6� '*+� �W*�-� �W*� �W* ȶ xW�W���,� 
,� �W�  m 1  *� W�  n 1  v*� wW*� zW*� xW*� �L+� +� �W�M*� �N-� #,� �W*,� �:� �-� �W*�� xW*1��� �W*�� xW*� �W*� �W�  o 1  x*� wW*� zW*� xW*� �L+� 
+�� �W�M*� �N-� #,� �W*,� �:� �-� �W*�� xW*1��� �W*�� xW*� �W*� �W� �$n    ?S(6���p?�L^?>t�?�[�  